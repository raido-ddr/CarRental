package com.raido.rental.logic.command;

import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class ActionCommand {

    public static final String METHOD_POST = "POST";

    public static final String METHOD_GET = "GET";

    protected static final ResourceBundle PAGE_NAME_BUNDLE =
            ResourceBundle.getBundle("page_names");

    public abstract String execute(HttpServletRequest request)
            throws CommandException;

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public Locale getCurrentLocale(HttpServletRequest request) {
        return (Locale) request.getSession().getAttribute("locale");
    }

}
