package com.raido.rental.logic.command.impl;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.controller.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ActionNotFoundCommand extends ActionCommand {

    private static ActionNotFoundCommand instance;

    private static final Lock lock = new ReentrantLock();

    private ActionNotFoundCommand() {}

    public static ActionNotFoundCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ActionNotFoundCommand();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    protected String processPostRequest(HttpServletRequest request) {
        return processRequest(request);
    }

    @Override
    protected String processGetRequest(HttpServletRequest request) {
        return processRequest(request);
    }

    private String processRequest(HttpServletRequest request)
    {
        return PAGE_NAME_BUNDLE.getString(PageName.ERROR);
    }


    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
