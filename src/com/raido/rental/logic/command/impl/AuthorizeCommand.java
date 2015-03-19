package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.User;
import com.raido.rental.logic.command.UserCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.util.resourcemanager.MessageBundle;
import com.raido.rental.controller.PageName;
import com.raido.rental.logic.ResourceName;
import com.raido.rental.logic.util.hash.MessageDigestHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AuthorizeCommand extends UserCommand {

    private static AuthorizeCommand instance;

    private static final Lock lock = new ReentrantLock();

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
    protected String processGetRequest(HttpServletRequest request) {
        return PAGE_NAME_BUNDLE.getString("authorization.page");
    }

    @Override
    protected String processPostRequest(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String hashedPassword =
                MessageDigestHelper.getInstance().getMd5Hash(password);

        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            User user = userDao.authorizeUser(login, hashedPassword);
            if(user != null) {
                setAuthorizationAttributes(request, user);

                switch (getCurrentUserRole(request)) {
                case "user":
                    return PAGE_NAME_BUNDLE.getString(PageName.USER_MAIN);
                case "admin":
                    return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_MAIN);
                default:
                    Locale locale = getCurrentLocale(request);
                    String message = MessageBundle.getString(ResourceName.COMMON_CAPTIONS,
                            "permission.denied", locale);
                    throw new CommandException(message);
                }

            } else {
                Locale locale = getCurrentLocale(request);
                request.setAttribute("authorizationError",
                        MessageBundle.getString(ResourceName.COMMON_CAPTIONS, "auth.error", locale));
                return PAGE_NAME_BUNDLE.getString("authorization.page");
            }

        } catch (DaoException e) {
            throw new CommandException(e);
        }
    }


}
