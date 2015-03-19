package com.raido.rental.logic.validator.strategy.impl;

import com.raido.rental.logic.util.resourcemanager.MessageBundle;
import com.raido.rental.logic.ResourceName;
import com.raido.rental.logic.validator.strategy.ValidationStrategy;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class CarValidationStrategy extends ValidationStrategy {

    private static class Holder {
        static final CarValidationStrategy INSTANCE
                = new CarValidationStrategy();
    }

    private static final String MAKE_REGEX =
            MessageBundle.getString("config", "make.regex");

    private static final String MODEL_REGEX =
            MessageBundle.getString("config", "model.regex");

    private static final String MILEAGE_REGEX =
            MessageBundle.getString("config", "mileage.regex");

    private static final String POWER_REGEX =
            MessageBundle.getString("config", "power.regex");

    private static final String SEAT_COUNT_REGEX =
            MessageBundle.getString("config", "seat.count.regex");

    private static final String DAILY_COST_REGEX =
            MessageBundle.getString("config", "daily.cost.regex");

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

        Locale locale =
                (Locale) request.getSession().getAttribute("locale");
        ResourceBundle bundle =
                ResourceBundle.getBundle(ResourceName.COMMON_CAPTIONS, locale);

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
