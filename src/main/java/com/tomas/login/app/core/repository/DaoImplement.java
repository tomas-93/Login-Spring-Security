package com.tomas.login.app.core.repository;

import com.tomas.login.app.core.repository.link.Dao;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.inject.Inject;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 22/08/2016
 */
public abstract class DaoImplement extends HibernateTemplate implements Dao
{
    @Inject
    public DaoImplement(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }
}
