package com.raido.rental.listener;

import com.raido.rental.logic.command.resolver.PermissionResolver;
import com.raido.rental.logic.exception.LogicalException;
import com.raido.rental.logic.exception.TechnicalException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ResourceBundle;

//@WebListener
public class PermissionContextListener implements ServletContextListener {

    private static final Logger LOGGER =
            Logger.getLogger(PermissionContextListener.class);

    public static final String PERMISSION_RESOLVER_ATTR =
            "permissionResolver";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext context = servletContextEvent.getServletContext();

        PermissionResolver permissionResolver;
        try {
            permissionResolver =
                    new PermissionResolver(context.getRealPath("permissions.xml"));
        } catch (TechnicalException | LogicalException e) {
            ResourceBundle bundle = ResourceBundle.getBundle("error_message");
            LOGGER.fatal(e);
            throw new RuntimeException(bundle.getString("app.unavailable"));
        }

        context.setAttribute(PERMISSION_RESOLVER_ATTR, permissionResolver);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
