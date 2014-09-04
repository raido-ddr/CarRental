package com.raido.rental.controller;

import com.raido.rental.logic.command.ActionCommand;
import com.raido.rental.logic.command.impl.AuthorizeCommand;
import com.raido.rental.logic.command.resolver.CommandResolver;
import com.raido.rental.logic.exception.LogicalException;
import com.raido.rental.logic.exception.TechnicalException;
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
import java.util.ResourceBundle;

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

        CommandResolver commandResolver;
        try {
            commandResolver = new CommandResolver(getServletContext()
                    .getRealPath("commands_mapping.xml"));
        } catch (TechnicalException | LogicalException e) {
            ResourceBundle bundle = ResourceBundle.getBundle("error_message");
            LOGGER.fatal(bundle.getString("command_resolver_error"));
            throw new ServletException(e);
        }

        getServletContext().setAttribute(COMMAND_RESOLVER_ATTR, commandResolver);

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

        String requestPath = request.getPathInfo();
        request.setAttribute("pathName", requestPath);

/*        String locale = request.getParameter("locale");u
        request.setAttribute("locale", locale);*/
        CommandResolver commandResolver =
                (CommandResolver) getServletContext().getAttribute(COMMAND_RESOLVER_ATTR);
        ActionCommand command = new AuthorizeCommand();//commandResolver.resolveCommand(requestPath);

        String commandName = command.getName();
        request.setAttribute("command", commandName);

        request.getRequestDispatcher("/jsp/result.jsp").forward(request, response);

    }


}

