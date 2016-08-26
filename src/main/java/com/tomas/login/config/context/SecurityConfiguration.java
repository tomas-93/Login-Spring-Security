package com.tomas.login.config.context;

import com.tomas.login.app.core.services.link.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 23/08/2016
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        order = 0,
        mode = AdviceMode.PROXY,
        proxyTargetClass = false
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserServices userServices;

    @Bean
    protected SessionRegistry sessionRegistryImpl()
    {
        return new SessionRegistryImpl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception
    {
        builder.userDetailsService(this.userServices)
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .and()
                    .eraseCredentials(true);
    }

    @Override
    public void configure(WebSecurity security)
    {
        //security.ignoring().antMatchers("/resource/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
        security.authorizeRequests()
                    .antMatchers("/app/admin")
                        .hasAnyAuthority("ADMIN_WEB")
                    .anyRequest()
                    .authenticated()
                .and().formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?fail")
                    .defaultSuccessUrl("/app")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/logout?succes")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                .and().sessionManagement()
                    .sessionFixation()
                    .changeSessionId()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(true)
                    .sessionRegistry(this.sessionRegistryImpl())
                .and().and().csrf()
                    .requireCsrfProtectionMatcher((csrf)->
                            {
                                String method = csrf.getMethod();
                                return !csrf.getServletPath().startsWith("/*") && (
                                        "POST".equals(method) || "PUT".equals(method) ||
                                                "PATCH".equals(method) || "DELETE".equals(method));
                            }
                    );

    }
}