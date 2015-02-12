package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.logic.command.CarCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.resourcemanager.MessageBundle;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AddCarCommand extends CarCommand {

    private static Lock lock = new ReentrantLock();

    private static AddCarCommand instance;

    private AddCarCommand() {}

    public static AddCarCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new AddCarCommand ();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    protected String processPostRequest(HttpServletRequest request)
            throws CommandException {

        Car car = createCarFromData(request);
        if(car != null) {

            CarDao carDao = DaoFactory.getInstance().getCarDao();
            try {
                carDao.createCar(car);
            } catch (DaoException e) {
                Locale locale =
                        (Locale) request.getSession().getAttribute("locale");
                throw new CommandException(MessageBundle
                        .getString("exception_message", "database.error", locale));
            }

            Locale locale = getCurrentLocale(request);
            request.setAttribute("successMessage",
                    MessageBundle.getString("success_message", "add.car", locale));

            return PAGE_NAME_BUNDLE.getString("admin.main.page");

        } else {
            setEnumAttributes(request);
            return PAGE_NAME_BUNDLE.getString("add.car.page");
        }


    }

    @Override
    protected String processGetRequest(HttpServletRequest request) {
        setEnumAttributes(request);
        return PAGE_NAME_BUNDLE.getString("add.car.page");
    }


}
