package com.tomas.login.app.core.services;

import com.tomas.login.app.core.dto.UserDto;
import com.tomas.login.app.core.repository.entity.UserprincipalAuthorityEntity;
import com.tomas.login.app.core.repository.entity.UserprincipalEntity;
import com.tomas.login.app.core.repository.link.UserRepository;
import com.tomas.login.app.core.services.link.UserServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 22/08/2016
 */
@Service
public class UserServicesImplement implements UserServices
{
    private final Logger log = LogManager.getLogger();

    private static final SecureRandom RANDOM;
    private static final int HASHING_ROUNDS = 10;

    static
    {
        try
        {
            RANDOM = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e)
        {
            throw new IllegalStateException(e);
        }
    }

    private final UserRepository userRepository;

    @Inject
    public UserServicesImplement(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void saveUser(UserDto userDto)
    {
        String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
        UserprincipalEntity userprincipalEntity = new UserprincipalEntity();
        userprincipalEntity.setUsername(userDto.getUsername());
        userprincipalEntity.setHashedPassword(BCrypt.hashpw(userDto.getPassword(), salt).getBytes());
        this.userRepository.save(userprincipalEntity);
    }

    @Override
    @Transactional
    public UserprincipalEntity loadUserByUsername(String s) throws UsernameNotFoundException
    {
        log.info("\n\nNOMBRE DE USUARIO ==> " + s +"\n\n");
        List user = this.userRepository.find("FROM UserprincipalEntity WHERE username = '"+ s+"'");
        UserprincipalEntity userprincipalEntity = user.isEmpty() ? null : (UserprincipalEntity) user.get(0);
        userprincipalEntity.getAuthorities().size();
        userprincipalEntity.getPassword();
        return userprincipalEntity;
    }
}
