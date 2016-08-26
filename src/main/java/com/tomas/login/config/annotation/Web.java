package com.tomas.login.config.annotation;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 17/08/2016
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
public @interface Web
{
    String value() default "";
}