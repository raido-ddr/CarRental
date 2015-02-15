package com.raido.rental.controller;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.exception.CommandException;
import com.raido.rental.logic.command.resolver.CommandResolver;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller/*")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String COMMAND_RESOLVER_ATTR =
            "command_resolver";

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(Controller.class);
        new DOMConfigurator().doConfigure("log4j.xml",
                LogManager.getLoggerRepository());
        LOGGER.setLevel(Level.INFO);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CommandResolver commandResolver =
                (CommandResolver) getServletContext().getAttribute(COMMAND_RESOLVER_ATTR);
        ActionCommand command =
                commandResolver.resolveCommand(request.getPathInfo());

        //String commandName = command.getName();
        //request.setAttribute("command", commandName);

        try {
            String nextPage = command.execute(request);
            request.getRequestDispatcher(nextPage).forward(request, response);
        } catch (CommandException e) {
            LOGGER.fatal(e);
            throw new RuntimeException(e);
        }

    }


}

