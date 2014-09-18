package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.UserDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.User;
import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.util.hash.MessageDigestHelper;
import com.raido.rental.logic.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RegisterCommand extends ActionCommand {

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
    public String execute(HttpServletRequest request) throws CommandException {

        if(METHOD_GET.equals(request.getMethod())) {
            return PAGE_NAME_BUNDLE.getString("registration.page");
        }

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

    private User createUserFromData(HttpServletRequest request) {

        User user = new User();
        if (DataValidator.getInstance().validateUser(request)) {
            user.setFirstName(request.getParameter("firstName").trim());
            user.setLastName(request.getParameter("lastName".trim()));
            user.setLogin(request.getParameter("login").trim());
            String password = request.getParameter("password").trim();
            user.setPassword(
                    MessageDigestHelper.getInstance().getMd5Hash(password));
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

    private void setAuthorizationAttributes(HttpServletRequest request,
            User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
    }
}
