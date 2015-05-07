package com.raido.rental.logic.validator;

import com.raido.rental.logic.validator.strategy.impl.CarValidationStrategy;
import com.raido.rental.logic.validator.strategy.impl.OrderValidationStrategy;
import com.raido.rental.logic.validator.strategy.impl.RentalPeriodValidationStrategy;
import com.raido.rental.logic.validator.strategy.impl.UserValidationStrategy;

import javax.servlet.http.HttpServletRequest;

public class DataValidator {

    private static class Holder {
        static final DataValidator INSTANCE
                = new DataValidator();
    }

    private DataValidator() {}

    public static DataValidator getInstance() {
        return Holder.INSTANCE;
    }

    public boolean validateUser(HttpServletRequest request) {
        ValidationContext context =
                new ValidationContext(UserValidationStrategy.getInstance());

        return context.executeStrategy(request);
    }

    public boolean validateOrder(HttpServletRequest request) {
        ValidationContext context =
                new ValidationContext(OrderValidationStrategy.getInstance());

        return context.executeStrategy(request);
    }

    public boolean validateRentalPeriod(HttpServletRequest request) {
        ValidationContext context =
                new ValidationContext(RentalPeriodValidationStrategy.getInstance());

        return context.executeStrategy(request);
    }

    public boolean validateCar(HttpServletRequest request) {
        ValidationContext context =
                new ValidationContext(CarValidationStrategy.getInstance());

        return context.executeStrategy(request);
    }


}
