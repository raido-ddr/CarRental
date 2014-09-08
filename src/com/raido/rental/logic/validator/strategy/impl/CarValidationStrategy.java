package com.raido.rental.logic.validator.strategy.impl;

import com.raido.rental.logic.validator.strategy.ValidationStrategy;

import javax.servlet.http.HttpServletRequest;

public class CarValidationStrategy implements ValidationStrategy {

    private static class Holder {
        static final CarValidationStrategy INSTANCE
                = new CarValidationStrategy();
    }

    private CarValidationStrategy() {}

    public static CarValidationStrategy getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean validate(HttpServletRequest request) {
        return false;
    }
}
