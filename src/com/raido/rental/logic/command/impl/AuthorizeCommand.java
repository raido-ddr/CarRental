package com.raido.rental.logic.command.impl;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Raido_DDR on 9/4/2014.
 */
public class AuthorizeCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request)
            throws CommandException {
        return null;
    }
}
