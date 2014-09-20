package com.raido.rental.dao;


import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.entity.Order;

import java.util.List;

public abstract class OrderDao extends Dao {

    public abstract void createOrder(Order order)
        throws DaoException;

    public abstract List<Order> findOrdersByCarId(int carId)
        throws DaoException;

}
