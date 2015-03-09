package com.raido.rental.logic.validator.strategy.impl;

import com.raido.rental.logic.util.hash.MessageDigestHelper;
import com.raido.rental.logic.validator.strategy.ValidationStrategy;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserValidationStrategy extends ValidationStrategy {

    private static final String FIRST_NAME_REGEX;

    private static final String LAST_NAME_REGEX;

    private static final String LOGIN_REGEX;

    private static final String PASSWORD_REGEX;

    private static final String EMAIL_REGEX;

    private static final String PASSPORT_REGEX;

    static {
        ResourceBundle bundle =
                ResourceBundle.getBundle("config");

        FIRST_NAME_REGEX = bundle.getString("first.name.regex");
        LAST_NAME_REGEX = bundle.getString("last.name.regex");
        LOGIN_REGEX = bundle.getString("login.regex");
        PASSWORD_REGEX = bundle.getString("password.regex");
        EMAIL_REGEX = bundle.getString("email.regex");
        PASSPORT_REGEX = bundle.getString("passport.regex");

    }

    private static class Holder {
        static final UserValidationStrategy INSTANCE
                = new UserValidationStrategy();
    }

    private UserValidationStrategy() {}

    public static UserValidationStrategy getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean validate(HttpServletRequest request) {

        //Validate text data
        String firstName = parameterHelper.getString(request, "firstName");
        String lastName = parameterHelper.getString(request, "lastName");
        String login = parameterHelper.getString(request, "login");
        String password = parameterHelper.getString(request, "password");
        String email = parameterHelper.getString(request, "email");
        String passportNumber = parameterHelper.getString(request, "passport");

        Locale locale =
                (Locale) request.getSession().getAttribute("locale");
        ResourceBundle bundle =
                ResourceBundle.getBundle("input_errors", locale);

        boolean dataIsCorrect = true;

        //Set error message parameters if needed
        if (! validateFirstName(firstName)) {
            request.setAttribute("fnameRule",
                    bundle.getString("first.name.rule"));
            dataIsCorrect = false;

        }

        if (! validateLastName(lastName)) {
            request.setAttribute("lnameRule",
                    bundle.getString("last.name.rule"));
            dataIsCorrect = false;
        }

        if (! validateLogin(login)) {
            request.setAttribute("loginRule",
                    bundle.getString("login.rule"));
            dataIsCorrect = false;
        }

        if (! validateEmail(email)) {
            request.setAttribute("emailRule",
                    bundle.getString("email.rule"));
            dataIsCorrect = false;
        }

        if (! validatePassword(password)) {
            request.setAttribute("passwordRule",
                    bundle.getString("password.rule"));
            dataIsCorrect = false;
        }

        if (! validatePassportNumber(passportNumber)) {
            request.setAttribute("passportRule",
                    bundle.getString("passport.rule"));
            dataIsCorrect = false;
        }

        //Validate dates
        String dobString = request.getParameter("dob");
        String licenseExpiryDate = request.getParameter("licenseExpiry");

        if (! validateDateOfBirth(dobString)) {
            request.setAttribute("dobRule",
                    bundle.getString("dob.rule"));
            dataIsCorrect = false;
        }

        if (! validateLicenseExpiryDate(licenseExpiryDate)) {
            request.setAttribute("licenseExpiryRule",
                    bundle.getString("license.expiry.rule"));
            dataIsCorrect = false;
        }

        return dataIsCorrect;
    }

    private boolean validateLicenseExpiryDate(String licenseExpiryString) {

        if(licenseExpiryString.isEmpty()) {
            return false;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date licenseExpiryDate = formatter.parse(licenseExpiryString);
            Date currentDate = new Date();
            return licenseExpiryDate.after(currentDate);

        } catch (ParseException e) {
            return false;
        }
    }

    private boolean validateDateOfBirth(String dobString) {

        if(dobString.isEmpty()) {
            return false;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date dateOfBirth = formatter.parse(dobString);
            Date currentDate = new Date();
            return (dateOfBirth.before(currentDate));

        } catch (ParseException e) {
            return false;
        }

    }

    private boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    private boolean validatePassportNumber(String passportNumber) {
        return passportNumber.matches(PASSPORT_REGEX);
    }

    private boolean validatePassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    private boolean validateLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    private boolean validateLastName(String lastName) {
        return lastName.matches(LAST_NAME_REGEX);
    }

    private boolean validateFirstName(String firstName) {
        return firstName.matches(FIRST_NAME_REGEX);
    }


}
