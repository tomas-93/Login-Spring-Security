package com.tomas.login.config.bootstrap;

import com.tomas.login.app.web.filter.LoggingFilter;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 23/08/2016
 */
@Order(3)
public class LoggingBootstrap implements WebApplicationInitializer
{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        FilterRegistration.Dynamic registration = servletContext.addFilter("LoggingFilter",new LoggingFilter());
        registration.addMappingForUrlPatterns(null, false, "/*");
    }
}
