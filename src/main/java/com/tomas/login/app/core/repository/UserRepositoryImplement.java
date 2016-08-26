package com.tomas.login.app.core.repository;

import com.tomas.login.app.core.repository.link.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 22/08/2016
 */
@Repository
public class UserRepositoryImplement extends DaoImplement implements UserRepository
{
    @Inject
    public UserRepositoryImplement(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }
}
