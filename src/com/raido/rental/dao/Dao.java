package com.raido.rental.dao;

import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.pool.ConnectionPool;
import com.raido.rental.dao.pool.exception.ConnectionPoolException;

import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * Created by Raido_DDR on 9/13/2014.
 */
public abstract class Dao {

    private static ConnectionPool connectionPool =
            ConnectionPool.getInstance();


    /**
     * Requests pool to provide a connection
     * and processes possible errors.
     *
     * @return Connection
     * @throws com.raido.rental.dao.exception.DaoException
     */
    protected Connection getPooledConnection() throws DaoException {

        try {
            return connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            ResourceBundle bundle =
                    ResourceBundle.getBundle("exception_message");
            throw new DaoException(
                    bundle.getString("database.error"), e);
        }
    }
}
