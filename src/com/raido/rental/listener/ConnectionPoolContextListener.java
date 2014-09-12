package com.raido.rental.listener;

import com.raido.rental.dao.pool.ConnectionPool;
import com.raido.rental.dao.pool.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ResourceBundle;

@WebListener
public class ConnectionPoolContextListener
        implements ServletContextListener {

    private static final Logger LOGGER =
            Logger.getLogger(ConnectionPoolContextListener.class);


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //Create db connection pool
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            /*
                Allows to detect connection pool errors before client
                attempts to use it.
             */
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            ResourceBundle bundle = ResourceBundle.getBundle("error_message");
            LOGGER.fatal(e);
            throw new RuntimeException(bundle.getString("app.unavailable"));
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().dispose();
    }
}
