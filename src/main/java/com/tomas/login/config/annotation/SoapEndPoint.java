package com.tomas.login.config.annotation;

import org.springframework.ws.server.endpoint.annotation.Endpoint;

import java.lang.annotation.*;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 21/08/2016
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Endpoint
public @interface SoapEndPoint
{
    String value() default "";
}