package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.entity.dbenum.BodyStyle;
import com.raido.rental.entity.dbenum.CarStatus;
import com.raido.rental.entity.dbenum.FuelType;
import com.raido.rental.entity.dbenum.TransmissionType;
import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AddCarCommand extends CarCommand {

    private static final String METHOD_POST = "POST";

    private static final String METHOD_GET = "GET";

    private static Lock lock = new ReentrantLock();

    private static AddCarCommand instance;

    private AddCarCommand () {}

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
    public String execute(HttpServletRequest request)
            throws CommandException {

        if(METHOD_GET.equals(request.getMethod())) {

            setEnumAttributes(request);
            return PAGE_NAME_BUNDLE.getString("add.car.page");
        }

        Car car = createCarFromData(request);
        if(car != null) {

            CarDao carDao = DaoFactory.getInstance().getCarDao();
            try {
                carDao.createCar(car);
            } catch (DaoException e) {
                Locale locale =
                        (Locale) request.getSession().getAttribute("locale");
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message", locale);
                throw new CommandException(bundle.getString("database.error"));
            }

            Locale locale =
                    (Locale) request.getSession().getAttribute("locale");
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
