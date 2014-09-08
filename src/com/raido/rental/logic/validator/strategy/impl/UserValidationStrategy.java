package com.raido.rental.logic.validator.strategy.impl;

import com.raido.rental.logic.validator.strategy.ValidationStrategy;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class UserValidationStrategy implements ValidationStrategy {

    private static final String FIRST_NAME_REGEX;

    private static final String LAST_NAME_REGEX;

    private static final String LOGIN_REGEX;

    private static final String PASSWORD_REGEX;

    private static final String EMAIL_REGEX;

    private static final String PASSPORT_REGEX;

    private static final String DATE_FORMAT;

    static {
        ResourceBundle bundle =
                ResourceBundle.getBundle("regex");

        FIRST_NAME_REGEX = bundle.getString("first.name.regex");
        LAST_NAME_REGEX = bundle.getString("last.name.regex");
        LOGIN_REGEX = bundle.getString("login.regex");
        PASSWORD_REGEX = bundle.getString("password.regex");
        EMAIL_REGEX = bundle.getString("email.regex");
        PASSPORT_REGEX = bundle.getString("passport.regex");

        DATE_FORMAT =
                ResourceBundle.getBundle("date").getString("date.format");

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
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String login = request.getParameter("login").trim();
        String password = request.getParameter("password").trim();
        String email = request.getParameter("email");
        String passportNumber = request.getParameter("passport").trim();

        ResourceBundle bundle =
                ResourceBundle.getBundle("input_errors");

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
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date dateOfBirth = formatter.parse(request.getParameter("dob"));
            Date licenseExpiryDate =
                    formatter.parse(request.getParameter("licenseExpiry"));

            if (! validateDateOfBirth(dateOfBirth)) {
                request.setAttribute("dobRule",
                        bundle.getString("dob.rule"));
                dataIsCorrect = false;
            }

            if (! validateLicenseExpiryDate(licenseExpiryDate)) {
                request.setAttribute("licenseExpiryRule",
                        bundle.getString("license.expiry.rule"));
                dataIsCorrect = false;
            }

        } catch (ParseException e) {
            dataIsCorrect = false;
        }

        return dataIsCorrect;
    }

    private boolean validateLicenseExpiryDate(Date licenseExpiryDate) {
        Date currentDate = new Date();
        return licenseExpiryDate.after(currentDate);
    }

    private boolean validateDateOfBirth(Date dob) {
        Date currentDate = new Date();
        return (dob.before(currentDate));
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
