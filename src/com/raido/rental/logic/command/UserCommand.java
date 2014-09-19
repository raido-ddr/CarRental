package com.raido.rental.logic.command;

import com.raido.rental.entity.User;
import com.raido.rental.logic.util.hash.MessageDigestHelper;
import com.raido.rental.logic.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Created by Raido_DDR on 19.09.2014.
 */
public abstract class UserCommand extends ActionCommand {

    public User createUserFromData(HttpServletRequest request) {

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

    public void setAuthorizationAttributes(HttpServletRequest request,
            User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
    }

}
