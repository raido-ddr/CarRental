package com.raido.rental.logic.command;

import com.raido.rental.entity.Order;
import com.raido.rental.entity.dbenum.OrderStatus;
import com.raido.rental.logic.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public abstract class OrderCommand extends ActionCommand {

    protected int getCurrentUserId(HttpServletRequest request) {
        return (int) request.getSession().getAttribute("userId");
    }

    protected Order createOrderFromData(HttpServletRequest request) {

        Order order = new Order();
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

        String statusString =
                parameterHelper.getUpperCaseString(request, "status");
        order.setStatus(OrderStatus.valueOf(statusString));

        float orderValue = parameterHelper.getFloat(request, "orderValue");
        order.setValue(orderValue);

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
