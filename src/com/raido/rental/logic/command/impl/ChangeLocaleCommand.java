package com.raido.rental.logic.command.impl;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;


public class ChangeLocaleCommand extends ActionCommand {


    @Override
    public String execute(HttpServletRequest request)
            throws CommandException {

        String localeTag = request.getParameter("locale");
        Locale locale = Locale.forLanguageTag(localeTag);
        request.getSession().setAttribute("locale", locale);


        return PAGE_NAME_BUNDLE.getString("welcome.page");
    }



}
