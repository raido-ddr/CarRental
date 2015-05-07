package com.raido.rental.logic.command;

import com.raido.rental.logic.ResourceName;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.util.requestparam.RequestParameterHelper;
import com.raido.rental.logic.util.resourcemanager.MessageBundle;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class ActionCommand {

    public static final String METHOD_POST = "POST";

    public static final String METHOD_GET = "GET";

    protected static final ResourceBundle PAGE_NAME_BUNDLE =
            ResourceBundle.getBundle("config");

    protected RequestParameterHelper parameterHelper =
            RequestParameterHelper.getInstance();

    public final String execute(HttpServletRequest request)
            throws CommandException {

        switch (request.getMethod()) {
        case METHOD_GET:
            return processGetRequest(request);
        case METHOD_POST:
            return processPostRequest(request);
        default:
            Locale locale = getCurrentLocale(request);
            throw new CommandException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "unsupported.method"));
        }
    }

    protected abstract String processGetRequest(HttpServletRequest request)
            throws CommandException;

    protected abstract String processPostRequest(HttpServletRequest request)
            throws CommandException;

    public String getName() {
        return this.getClass().getSimpleName();
    }

    protected Locale getCurrentLocale(HttpServletRequest request) {
        return (Locale) request.getSession().getAttribute("locale");
    }

    protected String getCurrentUserRole(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("role");
    }

}
