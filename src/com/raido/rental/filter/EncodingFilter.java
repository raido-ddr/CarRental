package com.raido.rental.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/*@WebFilter(urlPatterns = { "*//*" },
            initParams = {
                @WebInitParam(name = "encoding",
                        value = "UTF-8",
                        description = "Encoding parameter")
            })*/
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