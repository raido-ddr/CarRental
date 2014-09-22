package com.raido.rental.logic.command.impl;

import com.raido.rental.dao.OrderDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.factory.DaoFactory;
import com.raido.rental.entity.dbenum.OrderStatus;
import com.raido.rental.logic.command.OrderCommand;
import com.raido.rental.logic.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ChangeOrderStatusCommand extends OrderCommand {

    private static volatile ChangeOrderStatusCommand instance;

    private static Lock lock = new ReentrantLock();

    private ChangeOrderStatusCommand() {}

    public static ChangeOrderStatusCommand getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ChangeOrderStatusCommand();
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

        OrderStatus status =
                parameterHelper.getOrderStatus(request, "status");

        switch(status) {
        case CONFIRMED:
            return processConfirmedOrder(request);
        case ACTIVE:
            return processActiveOrder(request);
        case REJECTED:
            return processRejectedOrder(request);
        case DAMAGED:
            return processDamagedOrder(request);
        case ARCHIVED:
            return processArchivedOrder(request);
        case DELETED:
            return processDeletedOrder(request);
        default:
            return PAGE_NAME_BUNDLE.getString("admin.main.page");
        }

    }

    private String processDeletedOrder(HttpServletRequest request)
            throws CommandException {

        changeOrderStatus(request, OrderStatus.DELETED);
        return PAGE_NAME_BUNDLE.getString("admin.main.page");
    }

    private String processArchivedOrder(HttpServletRequest request)
            throws CommandException {

       changeOrderStatus(request, OrderStatus.ARCHIVED);
        return PAGE_NAME_BUNDLE.getString("admin.main.page");

    }

    private String processDamagedOrder(HttpServletRequest request)
            throws CommandException {

        int orderId = parameterHelper.getInt(request, "orderId");
        String damageDescription =
                parameterHelper.getString(request, "damageDescription");
        float penaltyAmount =
                parameterHelper.getFloat(request, "penaltyAmount");

        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        try {
            orderDao.reportDamage(orderId, damageDescription, penaltyAmount);
        } catch (DaoException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message", getCurrentLocale(request));
            throw new CommandException(bundle.getString("database.error"));
        }

        return PAGE_NAME_BUNDLE.getString("admin.main.page");
    }

    private String  processRejectedOrder(HttpServletRequest request)
            throws CommandException {

        int orderId = parameterHelper.getInt(request, "orderId");
        String rejectionReason =
                parameterHelper.getString(request, "rejectionReason");

        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        try {
            orderDao.rejectOrder(orderId, rejectionReason);
        } catch (DaoException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message", getCurrentLocale(request));
            throw new CommandException(bundle.getString("database.error"));
        }

        return PAGE_NAME_BUNDLE.getString("admin.main.page");
    }

    private String processActiveOrder(HttpServletRequest request)
            throws CommandException {

        changeOrderStatus(request, OrderStatus.ACTIVE);
        return PAGE_NAME_BUNDLE.getString("user.main.page");
    }

    private String processConfirmedOrder(HttpServletRequest request)
            throws CommandException {

        changeOrderStatus(request, OrderStatus.CONFIRMED);
        return PAGE_NAME_BUNDLE.getString("admin.main.page");
    }

    private void changeOrderStatus(HttpServletRequest request,
            OrderStatus status) throws CommandException {

        int orderId = parameterHelper.getInt(request, "orderId");

        OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
        try {
            orderDao.changeOrderStatus(orderId, status);
        } catch (DaoException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message", getCurrentLocale(request));
            throw new CommandException(bundle.getString("database.error"));
        }

    }
}
