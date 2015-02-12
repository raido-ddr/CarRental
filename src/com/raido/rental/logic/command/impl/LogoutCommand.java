package com.raido.rental.logic.command.impl;

import com.raido.rental.logic.command.UserCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.resourcemanager.MessageBundle;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Raido_DDR on 2/13/2015.
 */
public class LogoutCommand extends UserCommand {

    private static Lock lock = new ReentrantLock();

    private static LogoutCommand instance;

    private LogoutCommand() {}

    public static LogoutCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new LogoutCommand ();
            }
            lock.unlock();

        }
        return instance;
    }


    @Override
    protected String processGetRequest(HttpServletRequest request) throws CommandException {
        setDefaultAuthorizationAttributes(request);
        return PAGE_NAME_BUNDLE.getString("welcome.page");
    }

    @Override
    protected String processPostRequest(HttpServletRequest request) throws CommandException {
        Locale locale = getCurrentLocale(request);
        throw new CommandException(MessageBundle
                .getString("exception_message", "unsupported.method", locale));
    }
}
