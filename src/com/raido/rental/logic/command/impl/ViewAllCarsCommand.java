package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.logic.command.CarCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewAllCarsCommand extends CarCommand {

    private static Lock lock = new ReentrantLock();

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
    public String execute(HttpServletRequest request) throws CommandException {

        List<Car> cars;
        CarDao carDao = DaoFactory.getInstance().getCarDao();
        try {
            cars = carDao.selectAllCars();
        } catch (DaoException e) {
            Locale locale = getCurrentLocale(request);
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message", locale);
            throw new CommandException(bundle.getString("database.error"));
        }

        request.setAttribute("cars", cars);

        switch (getCurrentUserRole(request)) {
        case "admin":
            return PAGE_NAME_BUNDLE.getString("admin.view.cars.page");
        case "user":
            return PAGE_NAME_BUNDLE.getString("user.view.cars.page");
        default:
            Locale locale = getCurrentLocale(request);
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message", locale);
            throw new CommandException(bundle.getString("permission.denied"));
        }


    }
}
