package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.CarDao;
import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Car;
import com.raido.rental.entity.User;
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

public class AddCarCommand extends ActionCommand {

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

    private Car createCarFromData(HttpServletRequest request) {
        Car car = new Car();

        if(DataValidator.getInstance().validateCar(request)) {

            car.setMake(request.getParameter("make").trim());
            car.setModel(request.getParameter("model").trim());

            float mileage =
                    Float.valueOf(request.getParameter("mileage")
                            .trim());
            car.setMileage(mileage);

            float power =
                    Float.valueOf(request.getParameter("power")
                            .trim());
            car.setMileage(power);

            String fuelType = request.getParameter("fuelType").trim().toUpperCase();
            car.setFuelType(FuelType.valueOf(fuelType));

            String transmissionType =
                    request.getParameter("transmissionType").trim().toUpperCase();
            car.setTransmissionType(TransmissionType.valueOf(transmissionType));

            int seatCount =
                    Integer.valueOf(request.getParameter("seatCount").trim());
            car.setSeatCount(seatCount);

            String bodyStyle = request.getParameter("bodyStyle").trim().toUpperCase();
            car.setBodyStyle(BodyStyle.valueOf(bodyStyle));

            String status = request.getParameter("status").toUpperCase();
            car.setStatus(CarStatus.valueOf(status));
        } else {
            car = null;
        }

        return car;
    }

    private void setEnumAttributes(HttpServletRequest request) {

        List<String> statusOptions = new ArrayList<>();
        for(CarStatus status : CarStatus.values()) {
            statusOptions.add(status.getStatusValue());
        }
        request.setAttribute("statusOptions", statusOptions);

        List<String> fuelTypes = new ArrayList<>();
        for(FuelType type : FuelType.values()) {
            fuelTypes.add(type.getStatusValue());
        }
        request.setAttribute("fuelTypes", fuelTypes);

        List<String> bodyStyles = new ArrayList<>();
        for(BodyStyle style : BodyStyle.values()) {
            bodyStyles.add(style.getStatusValue());
        }
        request.setAttribute("bodyStyles", bodyStyles);

        List<String> transmissionTypes = new ArrayList<>();
        for(TransmissionType type : TransmissionType .values()) {
            transmissionTypes.add(type.getStatusValue());
        }
        request.setAttribute("transmissionTypes", transmissionTypes);
    }
}
