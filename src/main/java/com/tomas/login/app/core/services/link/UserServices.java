package com.tomas.login.app.core.services.link;

import com.tomas.login.app.core.dto.UserDto;
import com.tomas.login.app.core.repository.entity.UserprincipalAuthorityEntity;
import com.tomas.login.app.core.repository.entity.UserprincipalEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 22/08/2016
 */
@Validated
public interface UserServices extends UserDetailsService
{
    @Override
    UserprincipalEntity loadUserByUsername(String username);

    void saveUser(UserDto userDto);
}
