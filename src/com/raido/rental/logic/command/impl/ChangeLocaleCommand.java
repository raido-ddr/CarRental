package com.raido.rental.logic.command.impl;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ChangeLocaleCommand extends ActionCommand {

    private static volatile ChangeLocaleCommand instance;

    private static Lock lock = new ReentrantLock();

    private ChangeLocaleCommand() {}

    public static ChangeLocaleCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ChangeLocaleCommand();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    protected String processGetRequest(HttpServletRequest request) {
        return processRequest(request);
    }

    @Override
    protected String processPostRequest(HttpServletRequest request) throws CommandException {
        return processRequest(request);
    }

    private String processRequest(HttpServletRequest request) {
        String localeTag = request.getParameter("locale");
        Locale locale = Locale.forLanguageTag(localeTag);
        request.getSession().setAttribute("locale", locale);


        return PAGE_NAME_BUNDLE.getString("welcome.page");
    }


}
