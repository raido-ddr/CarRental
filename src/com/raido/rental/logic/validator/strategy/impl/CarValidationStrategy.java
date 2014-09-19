package com.raido.rental.logic.validator.strategy.impl;

import com.raido.rental.entity.dbenum.BodyStyle;
import com.raido.rental.entity.dbenum.FuelType;
import com.raido.rental.entity.dbenum.TransmissionType;
import com.raido.rental.logic.validator.strategy.ValidationStrategy;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class CarValidationStrategy extends ValidationStrategy {

    private static class Holder {
        static final CarValidationStrategy INSTANCE
                = new CarValidationStrategy();
    }

    private static final String MAKE_REGEX;

    private static final String MODEL_REGEX;

    private static final String MILEAGE_REGEX;

    private static final String POWER_REGEX;

    private static final String SEAT_COUNT_REGEX;

    private static final String DAILY_COST_REGEX;

    static {
        ResourceBundle bundle =
                ResourceBundle.getBundle("regex");

        MAKE_REGEX = bundle.getString("make.regex");
        MODEL_REGEX = bundle.getString("model.regex");
        MILEAGE_REGEX = bundle.getString("mileage.regex");
        POWER_REGEX = bundle.getString("power.regex");
        SEAT_COUNT_REGEX = bundle.getString("seat.count.regex");
        DAILY_COST_REGEX = bundle.getString("daily.cost.regex");

    }

    private CarValidationStrategy() {}

    public static CarValidationStrategy getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean validate(HttpServletRequest request) {

        String make = parameterHelper.getString(request, "make");
        String model = parameterHelper.getString(request, "model");
        String mileage =
                parameterHelper.getString(request, "mileage");
        String power =
                parameterHelper.getString(request, "power");
        String seatCount =
                parameterHelper.getString(request, "seatCount");
        String dailyCost =
                parameterHelper.getString(request, "dailyCost");

        /*String make = request.getParameter("make").trim();
        String model = request.getParameter("model").trim();
        String mileage = request.getParameter("mileage").trim();
        String power = request.getParameter("power").trim();
        String seatCount = request.getParameter("seatCount").trim();
        String dailyCost = request.getParameter("dailyCost").trim();*/

        Locale locale =
                (Locale) request.getSession().getAttribute("locale");
        ResourceBundle bundle =
                ResourceBundle.getBundle("input_errors", locale);

        boolean dataIsCorrect = true;

        if(! validateMake(make)) {
            request.setAttribute("makeRule",
                    bundle.getString("make.rule"));
            dataIsCorrect = false;
        }

        if(! validateModel(model)) {
            request.setAttribute("modelRule",
                    bundle.getString("model.rule"));
            dataIsCorrect = false;
        }

        if(! validateMileage(mileage)) {
            request.setAttribute("mileageRule",
                    bundle.getString("mileage.rule"));
            dataIsCorrect = false;
        }

        if(! validatePower(power)) {
            request.setAttribute("powerRule",
                    bundle.getString("power.rule"));
            dataIsCorrect = false;
        }

        if(! validateSeatCount(seatCount)) {
            request.setAttribute("seatCountRule",
                    bundle.getString("seat.count.rule"));
            dataIsCorrect = false;
        }

        if(! validateDailyCost(dailyCost)) {
            request.setAttribute("dailyCostRule",
                    bundle.getString("daily.cost.rule"));
            dataIsCorrect = false;
        }

        return dataIsCorrect;
    }

    private boolean validateDailyCost(String dailyCost) {
        return dailyCost.matches(DAILY_COST_REGEX);
    }

    private boolean validateSeatCount(String seatCount) {
        return seatCount.matches(SEAT_COUNT_REGEX);
    }

    private boolean validatePower(String power) {
        return power.matches(POWER_REGEX);
    }

    private boolean validateMileage(String mileage) {
        return mileage.matches(MILEAGE_REGEX);
    }

    private boolean validateModel(String model) {
        return model.matches(MODEL_REGEX);
    }

    private boolean validateMake(String make) {
        return make.matches(MAKE_REGEX);
    }


}
