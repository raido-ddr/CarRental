package com.raido.rental.filter;


import com.raido.rental.logic.command.resolver.PermissionResolver;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

/*@WebFilter(urlPatterns = {"/controller*//*"},
        servletNames = {"ControllerServlet"}
    )*/
public class PermissionFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request =
                (HttpServletRequest) servletRequest;
        HttpServletResponse response =
                (HttpServletResponse) servletResponse;


        HttpSession session = request.getSession();
        String userRole = (String) session.getAttribute("role");

        ServletContext context = filterConfig.getServletContext();
        PermissionResolver permissionResolver =
                (PermissionResolver) context.getAttribute("permissionResolver");

        String commandName = request.getPathInfo();
        if(! permissionResolver.isActionAllowed(commandName, userRole)) {
            ResourceBundle pageNameBundle =
                    ResourceBundle.getBundle("page_names");
            RequestDispatcher requestDispatcher =
                    request.getServletContext()
                            .getRequestDispatcher(pageNameBundle.getString("welcome.page"));

            requestDispatcher.forward(request, response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
