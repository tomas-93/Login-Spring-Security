package com.tomas.login.app.web.filter;

import org.apache.logging.log4j.ThreadContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 23/08/2016
 */
public class LoginFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        String id = UUID.randomUUID().toString();
        ThreadContext.put("id", id);
        try
        {
            ((HttpServletResponse) servletResponse).setHeader("X-App-Request-Id", id);
            filterChain.doFilter(servletRequest, servletResponse);

        }finally
        {
            ThreadContext.remove("id");
            ThreadContext.remove("username");
        }
    }

    @Override
    public void destroy()
    {

    }
}
