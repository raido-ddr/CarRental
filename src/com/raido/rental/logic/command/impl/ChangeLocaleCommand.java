package com.raido.rental.logic.command.impl;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Raido_DDR on 8/31/2014.
 */
public class ChangeLocaleCommand extends ActionCommand {


    @Override
    public String execute(HttpServletRequest request)
            throws CommandException {
        return null;
    }



}
