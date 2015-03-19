package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.entity.User;
import com.raido.rental.logic.command.OrderCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.util.resourcemanager.MessageBundle;
import com.raido.rental.controller.PageName;
import com.raido.rental.logic.ResourceName;
import com.raido.rental.logic.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChooseRentalPeriodCommand extends OrderCommand {

    private static ChooseRentalPeriodCommand instance;

    private static final Lock lock = new ReentrantLock();

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

        return PAGE_NAME_BUNDLE.getString(PageName.RENTAL_PERIOD);
    }

    @Override
    protected String processPostRequest(HttpServletRequest request)
            throws CommandException {

        if(DataValidator.getInstance().validateRentalPeriod(request)) {
            Date startDate = parameterHelper.getDate(request, "startDate");
            Date returnDate = parameterHelper.getDate(request, "returnDate");

            if(! checkLicenseValidity(request)) {
                Locale locale = getCurrentLocale(request);
                request.setAttribute("rentalPeriodRule",
                        MessageBundle.getString(ResourceName.COMMON_CAPTIONS, "invalid.license", locale));
                return PAGE_NAME_BUNDLE.getString(PageName.RENTAL_PERIOD);
            }

            //look for cars available for requested period
            List<Car> cars;
            CarDao carDao = DaoFactory.getInstance().getCarDao();
            try {
                cars = carDao.findCarsForPeriod(startDate, returnDate);
            } catch (DaoException e) {
                Locale locale = getCurrentLocale(request);
                throw new CommandException(MessageBundle
                        .getString(ResourceName.COMMON_CAPTIONS, "database.error", locale));
            }

            if(! cars.isEmpty()) {
                //allow user to choose a car to rent
                request.setAttribute("cars", cars);
                request.setAttribute("startDate", startDate);
                request.setAttribute("returnDate", returnDate);
                return PAGE_NAME_BUNDLE.getString(PageName.USER_VIEW_CARS);
            } else {
                //no cars available for requested period
                Locale locale = getCurrentLocale(request);
                request.setAttribute("resultMessage",
                        MessageBundle.getString(ResourceName.COMMON_CAPTIONS, "no.cars.available", locale));
                return PAGE_NAME_BUNDLE.getString(PageName.RENTAL_PERIOD);
            }

        } else {
            //invalid rental period requested
            return PAGE_NAME_BUNDLE.getString(PageName.RENTAL_PERIOD);
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
            throw new CommandException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "database.error"));
        }

        Date returnDate = parameterHelper.getDate(request, "returnDate");
        Date licenseExpiryDate = (Date) user.getLicenseExpiryDate();

        return (licenseExpiryDate.after(returnDate)
                || licenseExpiryDate.equals(returnDate));
    }
}
