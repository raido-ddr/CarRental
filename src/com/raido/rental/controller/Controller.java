package com.raido.rental.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller/*")
public class Controller extends HttpServlet {

    //private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String path = request.getPathInfo();
        request.setAttribute("pathName", path);
        request.getRequestDispatcher("jsp/result.jsp").forward(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();
        //request.setAttribute("pathName", path);
        request.getRequestDispatcher("jsp/result.jsp").forward(request, response);


        //Config.set( request, Config.FMT_LOCALE, new java.util.Locale("en_US") );
        //request.setAttribute("locale", );
        //request.getRequestDispatcher("jsp/result.jsp").forward(request, response);
    }


}

