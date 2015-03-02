package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.logic.command.CarCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.resourcemanager.MessageBundle;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewAllCarsCommand extends CarCommand {

    private static final Lock lock = new ReentrantLock();

    private static ViewAllCarsCommand instance;

    private ViewAllCarsCommand() {}

    public static ViewAllCarsCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ViewAllCarsCommand();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    protected String processGetRequest(HttpServletRequest request)
            throws CommandException {
        return processRequest(request);
    }

    @Override
    protected String processPostRequest(HttpServletRequest request)
            throws CommandException {
        return processRequest(request);
    }

    private String processRequest(HttpServletRequest request)
            throws CommandException {

        List<Car> cars;
        CarDao carDao = DaoFactory.getInstance().getCarDao();
        try {
            cars = carDao.selectAllCars();
        } catch (DaoException e) {
            Locale locale = getCurrentLocale(request);
            throw new CommandException(MessageBundle
                    .getString("exception_message", "database.error"));
        }

        request.setAttribute("cars", cars);

        switch (getCurrentUserRole(request)) {
        case "admin":
            return PAGE_NAME_BUNDLE.getString("admin.view.cars.page");
        case "user":
            return PAGE_NAME_BUNDLE.getString("user.view.cars.page");
        default:
            Locale locale = getCurrentLocale(request);
            throw new CommandException(MessageBundle
                    .getString("exception_message", "permission.denied"));
        }
    }
}
