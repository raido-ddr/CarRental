package com.raido.rental.dao;


import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.entity.Order;
import com.raido.rental.entity.OrderSummary;
import com.raido.rental.entity.dbenum.OrderStatus;

import java.util.List;

public abstract class OrderDao extends Dao {

    public abstract void createOrder(Order order)
        throws DaoException;

    public abstract List<Order> findOrdersByCarId(int carId)
        throws DaoException;

    public abstract void changeOrderStatus(int orderId, OrderStatus status)
            throws DaoException;

    public abstract void reportDamage(int orderId,
            String damageDescription, float penaltyAmount) throws DaoException;

    public abstract void rejectOrder(int orderId,
            String rejectionReason) throws DaoException;

    public abstract List<OrderSummary> getOrderSummariesByStatus(OrderStatus status)
            throws  DaoException;

    public abstract List<OrderSummary> getOrderSummariesForUser(OrderStatus status,
            int userId) throws DaoException;
}
