package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.User;
import com.raido.rental.logic.command.UserCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.resourcemanager.MessageBundle;
import com.raido.rental.logic.resourcemanager.PageName;
import com.raido.rental.logic.resourcemanager.ResourceName;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RegisterCommand extends UserCommand {

    private static RegisterCommand instance;

    private static final Lock lock = new ReentrantLock();

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
        return PAGE_NAME_BUNDLE.getString(PageName.REGISTRATION);
    }

    @Override
    protected String processPostRequest(HttpServletRequest request)
            throws CommandException {

        User user = createUserFromData(request);

        if(user != null) {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            try {

                if(userDao.isLoginUnique(user.getLogin())) {
                    int userId = userDao.createUser(user);
                    user.setId(userId);
                } else {
                    Locale locale =
                            (Locale) request.getSession().getAttribute("locale");
                    request.setAttribute("duplicateLoginError",
                            MessageBundle.getString(ResourceName.COMMON_CAPTIONS,
                            "duplicate.login", locale));
                    return PAGE_NAME_BUNDLE.getString(PageName.REGISTRATION);
                }
            } catch (DaoException e) {
                Locale locale = getCurrentLocale(request);
                throw new CommandException(MessageBundle
                        .getString(ResourceName.COMMON_CAPTIONS, "database.error"));
            }

            setAuthorizationAttributes(request, user);
            return PAGE_NAME_BUNDLE.getString(PageName.USER_MAIN);

        } else {
            return PAGE_NAME_BUNDLE.getString(PageName.REGISTRATION);
        }
    }


}
