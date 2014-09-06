package com.raido.rental.logic.command;

import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public abstract class ActionCommand {

    protected static final ResourceBundle PAGE_NAME_BUNDLE =
            ResourceBundle.getBundle("page_names");

    public abstract String execute(HttpServletRequest request)
            throws CommandException;

    public String getName() {
        return this.getClass().getSimpleName();
    }

}
