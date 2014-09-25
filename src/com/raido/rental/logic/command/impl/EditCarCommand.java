package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.logic.command.CarCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class EditCarCommand extends CarCommand {

    private static Lock lock = new ReentrantLock();

    private static EditCarCommand instance;

    private EditCarCommand() {}

    public static EditCarCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new EditCarCommand ();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    protected String processGetRequest(HttpServletRequest request)
            throws CommandException {

        int id = Integer.parseInt(request.getParameter("carId"));
        CarDao carDao = DaoFactory.getInstance().getCarDao();

        try {
            Car car = carDao.findCarById(id);
            setEnumAttributes(request);
            request.setAttribute("car", car);
            return PAGE_NAME_BUNDLE.getString("edit.car.page");
        } catch (DaoException e) {
            Locale locale = getCurrentLocale(request);
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message", locale);
            throw new CommandException(bundle.getString("database.error"));
        }
    }

    @Override
    protected String processPostRequest(HttpServletRequest request)
            throws CommandException {

        Car car = createCarFromData(request);
        car.setId(parameterHelper.getInt(request, "carId"));

        if(car != null) {

            CarDao carDao = DaoFactory.getInstance().getCarDao();
            try {
                carDao.editCar(car);
            } catch (DaoException e) {
                Locale locale = getCurrentLocale(request);
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message", locale);
                throw new CommandException(bundle.getString("database.error"));
            }

            Locale locale = getCurrentLocale(request);
            ResourceBundle bundle =
                    ResourceBundle.getBundle("success_message", locale);
            request.setAttribute("successMessage", bundle.getString("save.car"));

            return PAGE_NAME_BUNDLE.getString("admin.main.page");

        } else {
            setEnumAttributes(request);
            return PAGE_NAME_BUNDLE.getString("add.car.page");
        }
    }


}
