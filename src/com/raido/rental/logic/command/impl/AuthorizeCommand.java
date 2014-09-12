package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.User;
import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.util.hash.MessageDigestHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Raido_DDR on 9/4/2014.
 */
public class AuthorizeCommand extends ActionCommand {

    private static final String METHOD_POST = "POST";

    private static final String METHOD_GET = "GET";

    private static volatile AuthorizeCommand instance;

    private static Lock lock = new ReentrantLock();

    private AuthorizeCommand() {}

    public static AuthorizeCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new AuthorizeCommand();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request)
            throws CommandException {

        if(METHOD_GET.equals(request.getMethod())) {
            return PAGE_NAME_BUNDLE.getString("authorization.page");
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String hashedPassword =
                MessageDigestHelper.getInstance().getMd5Hash(password);

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            User user = userDao.authorizeUser(login, hashedPassword);
            if(user != null) {
                request.setAttribute("userId", user.getId());
                request.setAttribute("role", user.getRole());
                return PAGE_NAME_BUNDLE.getString("main.page");
            } else {
                Locale locale =
                        (Locale) request.getSession().getAttribute("locale");
                ResourceBundle bundle =
                        ResourceBundle.getBundle("input_errors", locale);
                request.setAttribute("authorizationError", bundle.getString("auth.error"));
                return PAGE_NAME_BUNDLE.getString("authorization.page");
            }

        } catch (DaoException e) {
            throw new CommandException(e);
        }
    }

}
