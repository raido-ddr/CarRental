package com.raido.rental.logic.validator.strategy;

import com.raido.rental.logic.resourcemanager.MessageBundle;
import com.raido.rental.logic.util.requestparam.RequestParameterHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public abstract class ValidationStrategy {

    protected static final String DATE_FORMAT =
            MessageBundle.getString("config", "date.format");

    protected RequestParameterHelper parameterHelper =
            RequestParameterHelper.getInstance();

    public abstract boolean validate(HttpServletRequest request);
}
