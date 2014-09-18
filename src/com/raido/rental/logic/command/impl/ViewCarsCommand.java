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


public class ViewCarsCommand extends CarCommand {

    private static Lock lock = new ReentrantLock();

    private static ViewCarsCommand instance;

    private ViewCarsCommand() {}

    public static ViewCarsCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ViewCarsCommand ();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        Car car = createCarFromData(request);
        if(car != null) {

            CarDao carDao = DaoFactory.getInstance().getCarDao();
            try {
                List<Car> cars = carDao.selectAllCars();
            } catch (DaoException e) {
                Locale locale = getCurrentLocale(request);
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message", locale);
                throw new CommandException(bundle.getString("database.error"));
            }

            Locale locale = getCurrentLocale(request);
            ResourceBundle bundle =
                    ResourceBundle.getBundle("success_message", locale);
            request.setAttribute("successMessage", bundle.getString("add.car"));

            return PAGE_NAME_BUNDLE.getString("admin.main.page");


        } else {
            setEnumAttributes(request);
            return PAGE_NAME_BUNDLE.getString("add.car.page");
        }

    }
}
