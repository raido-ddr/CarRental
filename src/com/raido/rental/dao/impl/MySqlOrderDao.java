package com.raido.rental.dao.impl;

import com.raido.rental.dao.OrderDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.entity.Car;
import com.raido.rental.entity.Order;
import com.raido.rental.entity.dbenum.OrderStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySqlOrderDao extends OrderDao {

    private static final Logger LOGGER =
            Logger.getLogger(MySqlOrderDao.class);

    private static final String SQL_CREATE_ORDER =
            "INSERT INTO orders (user_id, car_id, start_date, return_date," +
                    "status, order_value) VALUES(?,?,?,?,?,?)";

    private static final java.lang.String SQL_FIND_BY_CAR_ID =
            "SELECT * FROM orders WHERE car_id=?";

    private static volatile MySqlOrderDao instance;

    private static Lock lock = new ReentrantLock();

    private MySqlOrderDao() {}

    public static MySqlOrderDao getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new MySqlOrderDao();
            }
            lock.unlock();

        }
        return instance;
    }

    @Override
    public void createOrder(Order order) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getPooledConnection();

            preparedStatement =
                    connection.prepareStatement(SQL_CREATE_ORDER,
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getCarId());
            preparedStatement.setDate(3, order.getStartDate());
            preparedStatement.setDate(4, order.getReturnDate());
            preparedStatement.setString(5, order.getStatus().getValue());
            preparedStatement.setFloat(6, order.getValue());

            int rowsCount = preparedStatement.executeUpdate();
            if(rowsCount != 1) {
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message");
                throw new DaoException(bundle.getString("database.error"));
            }

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"),e);
        } try {
            if(connection != null) {
                connection.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Order> findOrdersByCarId(int carId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_CAR_ID,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, carId);

            resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while(resultSet.next()) {
                Order order = createOrderFromResultSet(resultSet);
                orders.add(order);
            }
            return orders;

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"), e);
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    private Order createOrderFromResultSet(ResultSet resultSet)
            throws SQLException {
        Order order = new Order();

        order.setId(resultSet.getInt(1));
        order.setUserId(resultSet.getInt(2));
        order.setCarId(resultSet.getInt(3));
        order.setStartDate(resultSet.getDate(4));
        order.setReturnDate(resultSet.getDate(5));
        order.setStatus(OrderStatus
                .valueOf(resultSet.getString(6).toUpperCase()));

        order.setValue(resultSet.getFloat(7));
        if(resultSet.wasNull()) {
            order.setValue(0);
        }

        order.setDamageId(resultSet.getInt(8));
        if(resultSet.wasNull()) {
            order.setDamageId(0);
        }

        order.setRejectionId(resultSet.getInt(9));
        if(resultSet.wasNull()) {
            order.setRejectionId(0);
        }

        return order;
    }


}
