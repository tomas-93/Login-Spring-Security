package com.tomas.login.app.core.repository.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 22/08/2016
 */
@Embeddable
@Table(name = "userprincipal_authority", schema = "springsecurity")
public class UserprincipalAuthorityEntity implements GrantedAuthority
{
    private String userAuthority;

    @Basic
    @Column(name = "Authority", nullable = false, length = 100)
    public String getUserAuthority()
    {
        return userAuthority;
    }

    public void setUserAuthority(String authority)
    {
        this.userAuthority = authority;
    }

    @Transient
    @Override
    public String getAuthority()
    {
        return this.userAuthority;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserprincipalAuthorityEntity that = (UserprincipalAuthorityEntity) o;

        if (userAuthority != null ? !userAuthority.equals(that.userAuthority) : that.userAuthority != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return userAuthority != null ? userAuthority.hashCode() : 0;
    }


}
