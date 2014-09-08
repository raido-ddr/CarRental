package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.User;
import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by Raido_DDR on 9/7/2014.
 */
public class RegisterCommand extends ActionCommand {

    private static final String METHOD_POST = "POST";

    private static final String METHOD_GET = "GET";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        if(METHOD_GET.equals(request.getMethod())) {
            return PAGE_NAME_BUNDLE.getString("registration.page");
        }

        User user = createUserFromData(request);
        if(user != null) {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            try {
                userDao.createUser(user);
            } catch (DaoException e) {
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message");
                throw new CommandException(bundle.getString("database.error"));
            }
            request.setAttribute("currentUser", user);
            return PAGE_NAME_BUNDLE.getString("main.page");
        } else {
            return PAGE_NAME_BUNDLE.getString("registration.page");
        }

    }

    private User createUserFromData(HttpServletRequest request) {

        User user = new User();
        if (DataValidator.getInstance().validateUser(request)) {
            user.setFirstName(request.getParameter("firstName").trim());
            user.setLastName(request.getParameter("lastName".trim()));
            user.setLogin(request.getParameter("login").trim());
            user.setPassword(request.getParameter("password").trim());
            user.setEmail(request.getParameter("email").trim());
            user.setRole("user");
            user.setDateOfBirth(
                    Date.valueOf(request.getParameter("dob")));
            user.setLicenseExpiryDate(
                    Date.valueOf(request.getParameter("licenseExpiry")));
            user.setPassportNumber(request.getParameter("passport").trim());

        } else {
            user = null;
        }

        return user;
    }
}
