package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.OrderDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.OrderSummary;
import com.raido.rental.entity.dbenum.OrderStatus;
import com.raido.rental.logic.command.OrderCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ViewOrdersCommand extends OrderCommand {

    private static volatile ViewOrdersCommand instance;

    private static Lock lock = new ReentrantLock();

    private ViewOrdersCommand() {}

    public static ViewOrdersCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ViewOrdersCommand();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    protected String processGetRequest(HttpServletRequest request)
            throws CommandException {
        return null;
    }

    @Override
    protected String processPostRequest(HttpServletRequest request)
            throws CommandException {

        switch (getCurrentUserRole(request)) {
        case "user":
            return processUserRequest(request);
        case "admin":
            return processAdminRequest(request);
        default:
            return PAGE_NAME_BUNDLE.getString("welcome.page");
        }
    }

    private String processAdminRequest(HttpServletRequest request)
            throws CommandException {

        OrderStatus status =
                parameterHelper.getOrderStatus(request, "status");

        List<OrderSummary> summaries = null;
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        try {
            summaries = orderDao.getOrderSummariesByStatus(status);
        } catch (DaoException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message", getCurrentLocale(request));
            throw new CommandException(bundle.getString("database.error"));
        }

        if(! summaries.isEmpty()) {
            request.setAttribute("summaries", summaries);
        } else {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("success_message", getCurrentLocale(request));
            request.setAttribute("emptyCategoryMessage",
                    bundle.getString("empty.order.category"));
        }
        return resolveViewPage(status);

    }

    private String processUserRequest(HttpServletRequest request)
            throws CommandException {

        OrderStatus status =
                parameterHelper.getOrderStatus(request, "status");

        List<OrderSummary> summaries = null;
        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        try {
            int userId = getCurrentUserId(request);
            summaries = orderDao.getOrderSummariesForUser(status, userId);
        } catch (DaoException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message", getCurrentLocale(request));
            throw new CommandException(bundle.getString("database.error"));
        }

        if(summaries != null) {
            request.setAttribute("summaries", summaries);
        } else {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("success_message", getCurrentLocale(request));
            request.setAttribute("emptyCategoryMessage",
                    bundle.getString("empty.order.category"));
        }
        return resolveViewPage(status);
    }

    private String resolveViewPage(OrderStatus status) {
        switch (status) {

        case NEW:
            return PAGE_NAME_BUNDLE.getString("admin.new.orders.page");
        case CONFIRMED:
            return PAGE_NAME_BUNDLE.getString("admin.confirmed.orders.page");
        case ACTIVE:
            return PAGE_NAME_BUNDLE.getString("admin.active.orders.page");
        case REJECTED:
            return PAGE_NAME_BUNDLE.getString("admin.rejected.orders.page");
        case DAMAGED:
            return PAGE_NAME_BUNDLE.getString("admin.damaged.orders.page");
        case ARCHIVED:
            return PAGE_NAME_BUNDLE.getString("admin.archived.orders.page");
        case DELETED:
            return PAGE_NAME_BUNDLE.getString("admin.deleted.orders.page");
        default:
            return PAGE_NAME_BUNDLE.getString("admin.main.page");
        }
    }




}
