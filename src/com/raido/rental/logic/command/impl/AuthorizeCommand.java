package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.User;
import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.util.MessageDigestHelper;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Raido_DDR on 9/4/2014.
 */
public class AuthorizeCommand extends ActionCommand {

    private static final String METHOD_POST = "POST";

    private static final String METHOD_GET = "GET";

    @Override
    public String execute(HttpServletRequest request)
            throws CommandException {

        if(METHOD_GET.equals(request.getMethod())) {
            return PAGE_NAME_BUNDLE.getString("authorization.page");
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(! validateLogin(login)) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("input_errors",
                        (Locale) request.getAttribute("locale"));
            request.setAttribute("loginError", bundle.getString("incorrect.login"));
            return PAGE_NAME_BUNDLE.getString("authorization.page");
        }

        if(! validatePassword(password)) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("input_errors",
                        (Locale) request.getAttribute("locale"));
            request.setAttribute("passwordError",
                    bundle.getString("incorrect.password"));
            return PAGE_NAME_BUNDLE.getString("authorization.page");
        }

        String hashedPassword = password;
                //MessageDigestHelper.getInstance().getMd5Hash(password);

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            User user = userDao.authorizeUser(login, hashedPassword);
            if(user != null) {
                request.setAttribute("currentUser", user);
                return PAGE_NAME_BUNDLE.getString("main.page");
            } else {
                ResourceBundle bundle =
                        ResourceBundle.getBundle("input_errors");
                            //(Locale) request.getAttribute("locale"));
                request.setAttribute("authorizationError", bundle.getString("auth.error"));
                return PAGE_NAME_BUNDLE.getString("authorization.page");
            }

        } catch (DaoException e) {
            throw new CommandException(e);
        }
    }

    private boolean validateLogin(String login) {
        return true;
    }

    private boolean validatePassword(String password) {
        return true;
    }
}
