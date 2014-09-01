package com.raido.rental.logic.command;

import com.raido.rental.logic.command.CommandException;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Raido_DDR on 8/31/2014.
 */
public interface ActionCommand {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException;

}
