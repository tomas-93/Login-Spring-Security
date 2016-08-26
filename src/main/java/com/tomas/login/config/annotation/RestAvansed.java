package com.tomas.login.config.annotation;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.lang.annotation.*;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 22/08/2016
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ControllerAdvice
public @interface RestAvansed
{
    String value() default "";
}