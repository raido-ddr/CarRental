package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.OrderDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.OrderSummary;
import com.raido.rental.entity.dbenum.OrderStatus;
import com.raido.rental.logic.command.OrderCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.resourcemanager.MessageBundle;
import com.raido.rental.logic.resourcemanager.PageName;
import com.raido.rental.logic.resourcemanager.ResourceName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ViewOrdersCommand extends OrderCommand {

    private static ViewOrdersCommand instance;

    private static final Lock lock = new ReentrantLock();

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
        return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_MAIN);
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
            return PAGE_NAME_BUNDLE.getString(PageName.WELCOME);
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
            throw new CommandException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "database.error"));
        }

        if(! summaries.isEmpty()) {
            request.setAttribute("summaries", summaries);
        } else {
            String message =  MessageBundle.getString(ResourceName.COMMON_CAPTIONS,
                    "empty.order.category");
            request.setAttribute("emptyCategoryMessage", message);
        }
        return resolveAdminViewPage(status);

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
            throw new CommandException(MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "database.error"));
        }

        if(! summaries.isEmpty()) {
            request.setAttribute("summaries", summaries);
        } else {
            String message =  MessageBundle.getString(ResourceName.COMMON_CAPTIONS,
                    "empty.order.category");
            request.setAttribute("emptyCategoryMessage", message);
        }
        return resolveUserViewPage(status);
    }

    private String resolveUserViewPage(OrderStatus status) {
        switch (status) {

        case NEW:
            return PAGE_NAME_BUNDLE.getString(PageName.USER_NEW_ORDERS);
        case CONFIRMED:
            return PAGE_NAME_BUNDLE.getString(PageName.USER_CONFIRMED_ORDERS);
        case ACTIVE:
            return PAGE_NAME_BUNDLE.getString(PageName.USER_ACTIVE_ORDERS);
        case REJECTED:
            return PAGE_NAME_BUNDLE.getString(PageName.USER_REJECTED_ORDERS);
        case DAMAGED:
            return PAGE_NAME_BUNDLE.getString(PageName.USER_DAMAGED_ORDERS);
        case ARCHIVED:
            return PAGE_NAME_BUNDLE.getString(PageName.USER_ARCHIVED_ORDERS);
        default:
            return PAGE_NAME_BUNDLE.getString(PageName.USER_MAIN);
        }
    }

    private String resolveAdminViewPage(OrderStatus status) {
        switch (status) {

        case NEW:
            return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_NEW_ORDERS);
        case CONFIRMED:
            return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_CONFIRMED_ORDERS);
        case ACTIVE:
            return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_ACTIVE_ORDERS);
        case REJECTED:
            return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_REJECTED_ORDERS);
        case DAMAGED:
            return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_DAMAGED_ORDERS);
        case ARCHIVED:
            return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_ARCHIVED_ORDERS);
        default:
            return PAGE_NAME_BUNDLE.getString(PageName.ADMIN_MAIN);
        }
    }




}
