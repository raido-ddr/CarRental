package com.raido.rental.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = { "/*" },
            initParams = {
                @WebInitParam(name = "encoding",
                        value = "UTF-8",
                        description = "Encoding parameter")
            })
public class EncodingFilter implements Filter {

    private String defaultEncoding;

    public void init(FilterConfig fConfig) throws ServletException {
        defaultEncoding = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String requestedEncoding = request.getCharacterEncoding();

        //Set default encoding if not supplied
        if((defaultEncoding != null)
                && (! defaultEncoding.equalsIgnoreCase(requestedEncoding))) {
            request.setCharacterEncoding(defaultEncoding);
            response.setCharacterEncoding(defaultEncoding);
        }
        chain.doFilter(request, response);
   }

   public void destroy() {
        defaultEncoding = null;
   }
}