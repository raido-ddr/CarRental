package com.raido.rental.logic.validator.strategy;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Raido_DDR on 9/7/2014.
 */
public interface ValidationStrategy {

    boolean validate(HttpServletRequest request);
}
