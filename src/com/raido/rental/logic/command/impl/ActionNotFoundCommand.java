package com.raido.rental.logic.command.impl;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ActionNotFoundCommand extends ActionCommand {

    private static volatile ActionNotFoundCommand instance;

    private static Lock lock = new ReentrantLock();

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
    public String execute(HttpServletRequest request)
            throws CommandException {
        return null;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
