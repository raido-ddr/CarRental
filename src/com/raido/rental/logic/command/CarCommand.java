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

    protected Car createCarFromData(HttpServletRequest request) {
        Car car = null;

        if(DataValidator.getInstance().validateCar(request)) {
            car = new Car();

            car.setMake(parameterHelper.getString(request, "make"));
            car.setModel(parameterHelper.getString(request, "model"));

            float mileage =
                    parameterHelper.getFloat(request, "mileage");
            car.setMileage(mileage);

            float power =
                    parameterHelper.getFloat(request, "power");
            car.setPower(power);

            String fuelType =
                    parameterHelper.getUpperCaseString(request, "fuelType");
            car.setFuelType(FuelType.valueOf(fuelType));

            String transmissionType =
                    parameterHelper.getUpperCaseString(request, "transmissionType");
            car.setTransmissionType(TransmissionType.valueOf(transmissionType));

            int seatCount =
                    parameterHelper.getInt(request, "seatCount");
            car.setSeatCount(seatCount);

            String bodyStyle =
                    parameterHelper.getUpperCaseString(request, "bodyStyle");
            car.setBodyStyle(BodyStyle.valueOf(bodyStyle));

            float dailyCost =
                    parameterHelper.getFloat(request, "dailyCost");
            car.setDailyCost(dailyCost);

            String status =
                    parameterHelper.getUpperCaseString(request, "status");
            car.setStatus(CarStatus.valueOf(status));
        }

        return car;
    }

    protected void setEnumAttributes(HttpServletRequest request) {

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
