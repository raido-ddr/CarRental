package com.raido.rental.listener;

import com.raido.rental.dao.pool.ConnectionPool;
import com.raido.rental.dao.pool.exception.ConnectionPoolException;
import com.raido.rental.logic.command.resolver.CommandResolver;
import com.raido.rental.logic.exception.LogicalException;
import com.raido.rental.logic.exception.TechnicalException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ResourceBundle;

@WebListener
public class CarRentalServletContextListener
        implements ServletContextListener {

    private static final Logger LOGGER =
            Logger.getLogger(CarRentalServletContextListener.class);

    public static final String COMMAND_RESOLVER_ATTR =
            "command_resolver";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //Create command resolver for routing purposes
        ServletContext context = servletContextEvent.getServletContext();

        CommandResolver commandResolver;
        try {
            commandResolver =
                    new CommandResolver(context.getRealPath("commands_mapping.xml"));
        } catch (TechnicalException | LogicalException e) {
            ResourceBundle bundle = ResourceBundle.getBundle("error_message");
            LOGGER.fatal(e);
            throw new RuntimeException(bundle.getString("app.unavailable"));
        }

        context.setAttribute(COMMAND_RESOLVER_ATTR, commandResolver);

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

    }
}
