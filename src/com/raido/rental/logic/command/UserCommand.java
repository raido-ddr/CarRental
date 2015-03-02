package com.raido.rental.logic.command;

import com.raido.rental.entity.User;
import com.raido.rental.logic.util.hash.MessageDigestHelper;
import com.raido.rental.logic.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public abstract class UserCommand extends ActionCommand {

    private static final String DEFAULT_REGISTRATION_ROLE = "user";

    private static final String DEFAULT_AUTHORIZATION_ROLE = "guest";

    protected User createUserFromData(HttpServletRequest request) {
        User user = null;

        if (DataValidator.getInstance().validateUser(request)) {
            user = new User();
            user.setFirstName(parameterHelper.getString(request, "firstName"));
            user.setLastName(parameterHelper.getString(request, "lastName"));
            user.setLogin(parameterHelper.getString(request, "login"));
            String password = parameterHelper.getString(request, "password");
            user.setPassword(
                    MessageDigestHelper.getInstance().getMd5Hash(password));
            user.setEmail(parameterHelper.getString(request, "email"));
            user.setRole(DEFAULT_REGISTRATION_ROLE);
            user.setDateOfBirth(parameterHelper.getDate(request, "dob"));
            user.setLicenseExpiryDate(parameterHelper.getDate(request, "licenseExpiry"));
            user.setPassportNumber(parameterHelper.getString(request, "passport"));
        }

        return user;
    }

    protected void setAuthorizationAttributes(HttpServletRequest request,
            User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
    }


}
