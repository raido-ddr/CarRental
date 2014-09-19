package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.User;
import com.raido.rental.logic.command.UserCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RegisterCommand extends UserCommand {

    private static volatile RegisterCommand instance;

    private static Lock lock = new ReentrantLock();

    private RegisterCommand () {}

    public static RegisterCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new RegisterCommand ();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    protected String processGetRequest(HttpServletRequest request)
            throws CommandException {
        return PAGE_NAME_BUNDLE.getString("registration.page");
    }

    @Override
    protected String processPostRequest(HttpServletRequest request)
            throws CommandException {

        User user = createUserFromData(request);

        if(user != null) {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            try {

                if(userDao.isLoginUnique(user.getLogin())) {
                    userDao.createUser(user);
                } else {
                    Locale locale =
                            (Locale) request.getSession().getAttribute("locale");
                    ResourceBundle bundle =
                            ResourceBundle.getBundle("input_errors", locale);
                    request.setAttribute("duplicateLoginError",
                            bundle.getString("duplicate.login"));
                    return PAGE_NAME_BUNDLE.getString("registration.page");
                }
            } catch (DaoException e) {
                Locale locale = getCurrentLocale(request);
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message", locale);
                throw new CommandException(bundle.getString("database.error"));
            }

            setAuthorizationAttributes(request, user);
            return PAGE_NAME_BUNDLE.getString("main.page");

        } else {
            return PAGE_NAME_BUNDLE.getString("registration.page");
        }
    }


}
