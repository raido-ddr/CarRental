package com.raido.rental.logic.validator;

import com.raido.rental.logic.validator.strategy.ValidationStrategy;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Raido_DDR on 9/7/2014.
 */
public class ValidationContext {

    private ValidationStrategy strategy;

    public ValidationContext(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean executeStrategy(HttpServletRequest request) {
        return  strategy.validate(request);
    }
}
