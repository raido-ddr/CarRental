package com.raido.rental.logic.command.impl;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;


public class ActionNotFoundCommand extends ActionCommand {
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
