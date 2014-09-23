package com.raido.rental.dao.impl;

import com.raido.rental.dao.OrderDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.entity.Car;
import com.raido.rental.entity.Order;
import com.raido.rental.entity.OrderSummary;
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
            "SELECT id, user_id, car_id, start_date, return_date," +
                    "status, order_value, rejection_reason, " +
                    "damage_description, penalty_amount FROM orders WHERE car_id=?";

    private static final java.lang.String SQL_UPDATE_STATUS =
            "UPDATE orders SET status=? WHERE id=?";

    private static final String SQL_REPORT_DAMAGE =
            "UPDATE orders SET status=?, damage_description=?," +
                    "penalty_amount=? WHERE id=?";

    private static final String SQL_REJECT =
            "UPDATE orders SET status=?, rejection_reason=? " +
                    "WHERE id=?";

    private static final String SQL_SUMMARY_BY_STATUS =
            "SELECT o.id, o.start_date, o.return_date, o.order_value," +
                    "o.rejection_reason, o.damage_description," +
                    "o.penalty_amount, u.first_name, u.last_name," +
                    "u.dob, u.email, u.license_expiry, c.make, c.model " +
                    "FROM orders o INNER JOIN users u ON u.id=o.user_id " +
                    "INNER JOIN cars c ON c.id=o.car_id " +
                    "WHERE o.status=?";

    private static final String SQL_SUMMARY_FOR_USER =
            "SELECT o.id, o.start_date, o.return_date, o.order_value," +
                    "o.rejection_reason, o.damage_description," +
                    "o.penalty_amount, u.first_name, u.last_name," +
                    "u.dob, u.email, u.license_expiry, c.make, c.model " +
                    "FROM orders o INNER JOIN users u ON u.id=o.user_id " +
                    "INNER JOIN cars c ON c.id=o.car_id " +
                    "WHERE u.id=? AND o.status=?";

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
            preparedStatement.close();
            connection.close();

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
                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void changeOrderStatus(int orderId, OrderStatus status)
            throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, status.getValue());
            preparedStatement.setInt(2, orderId);

            int rowCount = preparedStatement.executeUpdate();

            if(rowCount != 1) {
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message");
                throw new DaoException(bundle.getString("database.error"));
            }

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void reportDamage(int orderId, String damageDescription,
            float penaltyAmount) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_REPORT_DAMAGE,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, OrderStatus.DAMAGED.getValue());
            preparedStatement.setString(2, damageDescription);
            preparedStatement.setFloat(3, penaltyAmount);
            preparedStatement.setInt(4, orderId);

            int rowCount = preparedStatement.executeUpdate();

            if(rowCount != 1) {
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message");
                throw new DaoException(bundle.getString("database.error"));
            }

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }

    }

    @Override
    public void rejectOrder(int orderId, String rejectionReason) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_REJECT,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            preparedStatement.setString(1, OrderStatus.REJECTED.getValue());
            preparedStatement.setString(2, rejectionReason);
            preparedStatement.setInt(3, orderId);

            int rowCount = preparedStatement.executeUpdate();

            if(rowCount != 1) {
                ResourceBundle bundle =
                        ResourceBundle.getBundle("exception_message");
                throw new DaoException(bundle.getString("database.error"));
            }

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public List<OrderSummary> getOrderSummariesByStatus(OrderStatus status)
            throws  DaoException{

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_SUMMARY_BY_STATUS,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, status.getValue());

            resultSet = preparedStatement.executeQuery();

            List<OrderSummary> summaries = new ArrayList<>();
            while(resultSet.next()) {
                OrderSummary summary = createSummaryFromResultSet(resultSet);
                summaries.add(summary);
            }
            return summaries;

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }

    }

    @Override
    public List<OrderSummary> getOrderSummariesForUser(OrderStatus status,
            int userId) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getPooledConnection();
            preparedStatement = connection.prepareStatement(SQL_SUMMARY_FOR_USER,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, status.getValue());

            resultSet = preparedStatement.executeQuery();

            List<OrderSummary> summaries = new ArrayList<>();
            while(resultSet.next()) {
                OrderSummary summary = createSummaryFromResultSet(resultSet);
                summaries.add(summary);
            }
            return summaries;

        } catch (SQLException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(bundle.getString("database.error"), e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    private OrderSummary createSummaryFromResultSet(ResultSet resultSet)
            throws SQLException {

        OrderSummary summary = new OrderSummary();

        summary.setOrderId(resultSet.getInt("id"));
        summary.setStartDate(resultSet.getDate("start_date"));
        summary.setReturnDate(resultSet.getDate("return_date"));
        summary.setOrderValue(resultSet.getFloat("order_value"));
        summary.setRejectionReason(resultSet.getString("rejection_reason"));
        summary.setDamageDescription(resultSet.getString("damage_description"));
        summary.setPenaltyAmount(resultSet.getFloat("penalty_amount"));
        summary.setUserFirstName(resultSet.getString("first_name"));
        summary.setUserLastName(resultSet.getString("last_name"));
        summary.setUserDateOfBirth(resultSet.getDate("dob"));
        summary.setUserEmail(resultSet.getString("email"));
        summary.setLicenseExpiryDate(resultSet.getDate("license_expiry"));
        summary.setCarMake(resultSet.getString("make"));
        summary.setCarModel(resultSet.getString("model"));

        return summary;

    }

    private Order createOrderFromResultSet(ResultSet resultSet)
            throws SQLException {
        Order order = new Order();

        order.setId(resultSet.getInt("id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setCarId(resultSet.getInt("car_id"));
        order.setStartDate(resultSet.getDate("start_date"));
        order.setReturnDate(resultSet.getDate("return_date"));
        order.setStatus(OrderStatus
                .valueOf(resultSet.getString("status").toUpperCase()));

        order.setValue(resultSet.getFloat("order_value"));
        if(resultSet.wasNull()) {
            order.setValue(0);
        }

        order.setRejectionReason(resultSet.getString("rejection_reason"));
        if(resultSet.wasNull()) {
            order.setRejectionReason(null);
        }

        order.setDamageDescription(resultSet.getString("damage_description"));
        if(resultSet.wasNull()) {
            order.setDamageDescription(null);
        }

        order.setPenaltyAmount(resultSet.getFloat("penalty_amount"));
        if(resultSet.wasNull()) {
            order.setPenaltyAmount(0);
        }

        return order;
    }


}
