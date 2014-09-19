package com.raido.rental.logic.validator.strategy.impl;

import com.raido.rental.logic.validator.strategy.ValidationStrategy;

import javax.servlet.http.HttpServletRequest;


public class OrderValidationStrategy extends ValidationStrategy {

    private static class Holder {
        static final OrderValidationStrategy INSTANCE
                = new OrderValidationStrategy();
    }

    private OrderValidationStrategy() {}

    public static OrderValidationStrategy getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean validate(HttpServletRequest request) {
        return false;
    }
}
