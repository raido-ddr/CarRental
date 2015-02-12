package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.OrderDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.Order;
import com.raido.rental.logic.command.OrderCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.resourcemanager.MessageBundle;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlaceOrderCommand extends OrderCommand {

    private static PlaceOrderCommand instance;

    private static Lock lock = new ReentrantLock();

    private PlaceOrderCommand() {}

    public static PlaceOrderCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new PlaceOrderCommand();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    protected String processGetRequest(HttpServletRequest request) throws CommandException {
        String id = request.getParameter("carId");
        String make = request.getParameter("make");
        float dailyCost = parameterHelper.getFloat(request, "dailyCost");
        Date startDate = parameterHelper.getDate(request, "startDate");
        Date returnDate = parameterHelper.getDate(request, "returnDate");
        float orderValue = calculateOrderValue(dailyCost, startDate, returnDate);
        request.setAttribute("orderValue", orderValue);

        return PAGE_NAME_BUNDLE.getString("place.order.page");
    }

    @Override
    protected String processPostRequest(HttpServletRequest request) throws CommandException {

        String carId = request.getParameter("carId");
        String userId = request.getParameter("userId");
        Date startDate = parameterHelper.getDate(request, "startDate");
        Date returnDate = parameterHelper.getDate(request, "returnDate");

        String orderValue = request.getParameter("orderValue");

        Order order = createOrderFromData(request);

        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        Locale locale = getCurrentLocale(request);
        try {
            orderDao.createOrder(order);
        } catch (DaoException e) {
            throw new CommandException(MessageBundle
                    .getString("exception_message", "database.error"));
        }

        request.setAttribute("successMessage",
                MessageBundle.getString("success_message", "place.order.success", locale));
        return PAGE_NAME_BUNDLE.getString("user.main.page");
    }


}
