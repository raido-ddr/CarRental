package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.entity.User;
import com.raido.rental.logic.command.OrderCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChooseRentalPeriodCommand extends OrderCommand {

    private static volatile ChooseRentalPeriodCommand instance;

    private static Lock lock = new ReentrantLock();

    private ChooseRentalPeriodCommand() {}

    public static ChooseRentalPeriodCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ChooseRentalPeriodCommand();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    protected String processGetRequest(HttpServletRequest request)
            throws CommandException {

        return PAGE_NAME_BUNDLE.getString("rental.period.page");
    }

    @Override
    protected String processPostRequest(HttpServletRequest request)
            throws CommandException {

        if(DataValidator.getInstance().validateRentalPeriod(request)) {
            Date startDate = parameterHelper.getDate(request, "startDate");
            Date returnDate = parameterHelper.getDate(request, "returnDate");

            if(! checkLicenseValidity(request)) {
                Locale locale = getCurrentLocale(request);
                ResourceBundle bundle =
                        ResourceBundle.getBundle("input_errors", locale);
                request.setAttribute("rentalPeriodRule",
                        bundle.getString("invalid.license"));
                return PAGE_NAME_BUNDLE.getString("rental.period.page");
            }

            //look for cars available for requested period
            List<Car> cars;
            CarDao carDao = DaoFactory.getInstance().getCarDao();
            try {
                cars = carDao.findCarsForPeriod(startDate, returnDate);
            } catch (DaoException e) {
                Locale locale = getCurrentLocale(request);
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message", locale);
                throw new CommandException(bundle.getString("database.error"));
            }

            if(! cars.isEmpty()) {
                //allow user to choose a car to rent
                request.setAttribute("cars", cars);
                request.setAttribute("startDate", startDate);
                request.setAttribute("returnDate", returnDate);
                return PAGE_NAME_BUNDLE.getString("user.view.cars.page");
            } else {
                //no cars available for requested period
                Locale locale = getCurrentLocale(request);
                ResourceBundle bundle =
                        ResourceBundle.getBundle("input_errors", locale);
                request.setAttribute("resultMessage", bundle.getString("no.cars.available"));
                return PAGE_NAME_BUNDLE.getString("rental.period.page");
            }

        } else {
            //invalid rental period requested
            return PAGE_NAME_BUNDLE.getString("rental.period.page");
        }

    }

    private boolean checkLicenseValidity(HttpServletRequest request)
            throws CommandException {

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        int userId = getCurrentUserId(request);

        User user;
        try {
            user = userDao.findUserById(userId);
        } catch (DaoException e) {
            Locale locale = getCurrentLocale(request);
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message", locale);
            throw new CommandException(bundle.getString("database.error"));
        }

        Date returnDate = parameterHelper.getDate(request, "returnDate");
        Date licenseExpiryDate = (Date) user.getLicenseExpiryDate();

        return (licenseExpiryDate.after(returnDate)
                || licenseExpiryDate.equals(returnDate));
    }
}
