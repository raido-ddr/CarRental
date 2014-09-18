package com.raido.rental.logic.command.impl;

import com.raido.rental.entity.Car;
import com.raido.rental.entity.dbenum.BodyStyle;
import com.raido.rental.entity.dbenum.CarStatus;
import com.raido.rental.entity.dbenum.FuelType;
import com.raido.rental.entity.dbenum.TransmissionType;
import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public abstract class CarCommand extends ActionCommand {

    public Car createCarFromData(HttpServletRequest request) {
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

    public void setEnumAttributes(HttpServletRequest request) {

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
