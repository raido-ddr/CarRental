package com.raido.rental.listener;

import com.raido.rental.dao.exception.DaoException;
import com.raido.rental.logic.command.resolver.CommandResolver;
import com.raido.rental.logic.exception.LogicalException;
import com.raido.rental.logic.exception.TechnicalException;
import com.raido.rental.logic.resourcemanager.MessageBundle;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ResourceBundle;

@WebListener
public class CommandResolverContextListener
        implements ServletContextListener {

    private static final Logger LOGGER =
            Logger.getLogger(CommandResolverContextListener.class);

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
            LOGGER.fatal(e);
            throw new RuntimeException( MessageBundle
                    .getString("error_message", "app.unavailable"));
        }

        context.setAttribute(COMMAND_RESOLVER_ATTR, commandResolver);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
