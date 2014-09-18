package com.raido.rental.logic.command;

import com.raido.rental.entity.Car;
import com.raido.rental.entity.dbenum.BodyStyle;
import com.raido.rental.entity.dbenum.CarStatus;
import com.raido.rental.entity.dbenum.FuelType;
import com.raido.rental.entity.dbenum.TransmissionType;
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
            car.setPower(power);

            String fuelType = request.getParameter("fuelType").trim().toUpperCase();
            car.setFuelType(FuelType.valueOf(fuelType));

            String transmissionType =
                    request.getParameter("transmissionType").trim().toUpperCase();
            car.setTransmissionType(TransmissionType.valueOf(transmissionType));

            String seatCount =
                    request.getParameter("seatCount").trim();
            car.setSeatCount(Integer.valueOf(seatCount));

            String bodyStyle = request.getParameter("bodyStyle").trim().toUpperCase();
            car.setBodyStyle(BodyStyle.valueOf(bodyStyle));

            String dailyCost = request.getParameter("dailyCost").trim();
            car.setDailyCost(Float.parseFloat(dailyCost));

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
            statusOptions.add(status.getValue());
        }
        request.setAttribute("statusOptions", statusOptions);

        List<String> fuelTypes = new ArrayList<>();
        for(FuelType type : FuelType.values()) {
            fuelTypes.add(type.getValue());
        }
        request.setAttribute("fuelTypes", fuelTypes);

        List<String> bodyStyles = new ArrayList<>();
        for(BodyStyle style : BodyStyle.values()) {
            bodyStyles.add(style.getValue());
        }
        request.setAttribute("bodyStyles", bodyStyles);

        List<String> transmissionTypes = new ArrayList<>();
        for(TransmissionType type : TransmissionType .values()) {
            transmissionTypes.add(type.getValue());
        }
        request.setAttribute("transmissionTypes", transmissionTypes);
    }

}
