package com.raido.rental.logic.validator.strategy.impl;

import com.raido.rental.logic.validator.strategy.ValidationStrategy;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Raido_DDR on 19.09.2014.
 */
public class RentalPeriodValidationStrategy extends ValidationStrategy {

    private static class Holder {
        static final RentalPeriodValidationStrategy INSTANCE
                = new RentalPeriodValidationStrategy();
    }

    private RentalPeriodValidationStrategy() {}

    public static RentalPeriodValidationStrategy getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean validate(HttpServletRequest request) {

        boolean dataIsCorrect = true;

        String startDateString = parameterHelper.getString(request, "startDate");
        String returnDateString = parameterHelper.getString(request, "returnDate");

        Locale locale =
                (Locale) request.getSession().getAttribute("locale");
        ResourceBundle bundle =
                ResourceBundle.getBundle("input_errors", locale);

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date startDate = formatter.parse(startDateString);
            Date returnDate = formatter.parse(returnDateString);
            Date currentDate = new Date();

            if(! validateStartDate(startDate, currentDate)) {
                dataIsCorrect = false;
                request.setAttribute("rentalPeriodRule",
                        bundle.getString("bad.start.date"));

            }

            if(! validateReturnDate(startDate, returnDate)) {
                dataIsCorrect = false;
                request.setAttribute("rentalPeriodRule",
                        bundle.getString("bad.return.date"));

            }

        } catch (ParseException e) {
            dataIsCorrect = false;
            request.setAttribute("rentalPeriodRule",
                    bundle.getString("bad.period"));
        }

        return dataIsCorrect;
    }

    private boolean validateStartDate(Date startDate, Date currentDate) {
        return startDate.after(currentDate);
    }

    private boolean validateReturnDate(Date startDate, Date returnDate) {
        return returnDate.after(startDate);
    }

}
