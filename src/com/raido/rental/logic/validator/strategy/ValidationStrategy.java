package com.raido.rental.logic.validator.strategy;

import com.raido.rental.logic.util.requestparam.RequestParameterHelper;

import javax.servlet.http.HttpServletRequest;

public abstract class ValidationStrategy {

    public RequestParameterHelper parameterHelper =
            RequestParameterHelper.getInstance();

    public abstract boolean validate(HttpServletRequest request);
}
