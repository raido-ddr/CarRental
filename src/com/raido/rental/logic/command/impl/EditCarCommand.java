package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.logic.command.CarCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.resourcemanager.MessageBundle;
import com.raido.rental.logic.resourcemanager.PageName;
import com.raido.rental.logic.resourcemanager.ResourceName;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class EditCarCommand extends CarCommand {

    private static final Lock lock = new ReentrantLock();

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
            return PAGE_NAME_BUNDLE.getString(PageName.EDIT_CAR);
        } catch (DaoException e) {
            throw new CommandException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "database.error"));
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
                throw new CommandException(MessageBundle
                        .getString(ResourceName.COMMON_CAPTIONS, "database.error"));
            }

            Locale locale = getCurrentLocale(request);
            String message = MessageBundle.getString(ResourceName.COMMON_CAPTIONS,
                    "save.car", locale);
            request.setAttribute("successMessage", message);

            return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_MAIN);

        } else {
            setEnumAttributes(request);
            return PAGE_NAME_BUNDLE.getString(PageName.ADD_CAR);
        }
    }


}
