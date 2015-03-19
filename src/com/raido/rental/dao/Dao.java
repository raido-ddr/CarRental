package com.raido.rental.dao;

import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.dao.pool.ConnectionPool;
import com.raido.rental.dao.pool.exception.ConnectionPoolException;
import com.raido.rental.logic.util.resourcemanager.MessageBundle;
import com.raido.rental.logic.ResourceName;

import java.sql.Statement;
import java.sql.Connection;

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
            throw new DaoException( MessageBundle
                    .getString(ResourceName.COMMON_CAPTIONS, "database.error"),e);
        }
    }

    protected void closePooledConnection(Connection connection,
            Statement statement) {
        connectionPool.closeConnection(connection, statement);
    }
}
