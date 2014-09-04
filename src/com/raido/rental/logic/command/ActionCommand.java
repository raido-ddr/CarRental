package com.raido.rental.logic.command;

import com.raido.rental.logic.command.CommandException;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ActionCommand {

    public abstract String execute(HttpServletRequest request)
            throws CommandException;

    public String getName() {
        return this.getClass().getSimpleName();
    }

}
