package com.raido.rental.logic.command.impl;

import com.raido.rental.logic.command.UserCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.resourcemanager.MessageBundle;
import com.raido.rental.logic.resourcemanager.PageName;
import com.raido.rental.logic.resourcemanager.ResourceName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogoutCommand extends UserCommand {

    private static final Lock lock = new ReentrantLock();

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
        HttpSession session = request.getSession();
        session.invalidate();
        return PAGE_NAME_BUNDLE.getString(PageName.WELCOME);
    }

    @Override
    protected String processPostRequest(HttpServletRequest request) throws CommandException {
        Locale locale = getCurrentLocale(request);
        throw new CommandException(MessageBundle
                .getString(ResourceName.COMMON_CAPTIONS, "unsupported.method", locale));
    }
}
