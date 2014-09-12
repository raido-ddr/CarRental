package com.raido.rental.dao.impl;

import com.raido.rental.dao.AdminDao;
import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.pool.ConnectionPool;
import com.raido.rental.dao.pool.exception.ConnectionPoolException;
import com.raido.rental.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySqlAdminDao implements AdminDao {

    private static final Logger LOGGER =
            Logger.getLogger(MySqlAdminDao.class);

    private static volatile MySqlAdminDao instance;

    private static Lock lock = new ReentrantLock();

    private MySqlAdminDao() {}

    public static MySqlAdminDao getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new MySqlAdminDao();
            }
            lock.unlock();

        }
        return instance;
    }

    private Connection getPooledConnection() throws DaoException {

        Connection connection;
        try {
            return ConnectionPool.getInstance().takeConnection();
        } catch (ConnectionPoolException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(
                    bundle.getString("connection.pool.error"), e);
        }
    }



}
