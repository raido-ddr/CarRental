package com.raido.rental.logic.command.impl;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Raido_DDR on 8/31/2014.
 */
public class ChangeLocaleCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException {
        return null;



    }
}
