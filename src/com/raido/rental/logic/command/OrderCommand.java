package com.raido.rental.logic.command;

import com.raido.rental.entity.Order;
import com.raido.rental.logic.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Created by Raido_DDR on 19.09.2014.
 */
public abstract class OrderCommand extends ActionCommand {

    protected int getCurrentUserId(HttpServletRequest request) {
        String userIdString = (String)request.getSession().getAttribute("userId");
        return Integer.parseInt(userIdString);
    }

    protected Order createOrderFromData(HttpServletRequest request) {
        Order order = null;

        if(DataValidator.getInstance().validateOrder(request)) {

            order = new Order();
            order.setUserId(getCurrentUserId(request));

            int carId =
                    parameterHelper.getInt(request, "carId");
            order.setCarId(carId);

            Date startDate =
                    parameterHelper.getDate(request, "startDate");
            order.setStartDate(startDate);

            Date returnDate =
                    parameterHelper.getDate(request, "returnDate");
            order.setReturnDate(returnDate);

            float dailyCost =
                    parameterHelper.getFloat(request, "dailyCost");
            float orderValue = calculateOrderValue(dailyCost, startDate, returnDate);
            order.setValue(orderValue);
        }

        return order;
    }

    protected float calculateOrderValue(float dailyCost, Date startDate, Date returnDate) {
        long startTime = startDate.getTime();
        long endTime = returnDate.getTime();

        long differenceInDays = (endTime - startTime)
                / (1000 * 60 * 60 * 24);

        return differenceInDays * dailyCost;
    }


}
