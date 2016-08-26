package com.tomas.login.app.web.controller;

import com.tomas.login.app.core.dto.UserDto;
import com.tomas.login.app.core.repository.entity.UserprincipalEntity;
import com.tomas.login.app.web.controller.route.RouterAndView;
import com.tomas.login.config.annotation.Web;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 22/08/2016
 */
@Web
public class IndexController
{
    private final Logger log = LogManager.getLogger();

    @RequestMapping(value = RouterAndView.R_LOGIN, method = RequestMethod.GET)
    public ModelAndView getLogin( Map<String, Object> model)
    {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof UserprincipalEntity)
            return new ModelAndView(new RedirectView(RouterAndView.R_APP, true));
        model.put("userDto", new UserDto());
        return new ModelAndView(RouterAndView.V_LOGIN_INDEX);
    }
    @PreAuthorize("hasAuthority('ADMIN_WEB  ') or hasAuthority('USER_VIEW')")
    @RequestMapping(value = RouterAndView.R_APP, method = RequestMethod.GET)
    public ModelAndView getApp(@AuthenticationPrincipal UserprincipalEntity user)
    {
        return new ModelAndView(RouterAndView.V_LOGIN_APP);
    }

    @PreAuthorize("hasAuthority('ADMIN_WEB')")
    @RequestMapping(value = RouterAndView.R_APP_ADMIN, method = RequestMethod.GET)
    public ModelAndView getAppAdmin()
    {
        return new ModelAndView(RouterAndView.V_LOGIN_APP_ADMIN);
    }
}
