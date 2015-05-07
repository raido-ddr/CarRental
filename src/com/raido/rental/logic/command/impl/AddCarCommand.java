package com.raido.rental.logic.command.impl;

import com.raido.rental.controller.PageName;
import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.logic.ResourceName;
import com.raido.rental.logic.command.CarCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.util.resourcemanager.MessageBundle;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AddCarCommand extends CarCommand {

    private static final Lock lock = new ReentrantLock();

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
                        .getString(ResourceName.COMMON_CAPTIONS, "database.error", locale));
            }

            Locale locale = getCurrentLocale(request);
            request.setAttribute("successMessage",
                    MessageBundle.getString(ResourceName.COMMON_CAPTIONS, "add.car", locale));

            return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_MAIN);

        } else {
            setEnumAttributes(request);
            return PAGE_NAME_BUNDLE.getString(PageName.ADD_CAR);
        }


    }

    @Override
    protected String processGetRequest(HttpServletRequest request) {
        setEnumAttributes(request);
        return PAGE_NAME_BUNDLE.getString(PageName.ADD_CAR);
    }


}
