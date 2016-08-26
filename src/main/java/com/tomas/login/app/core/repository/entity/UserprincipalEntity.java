package com.tomas.login.app.core.repository.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Tomas Yussef Galicia Guzman
 *         email: tomasyussef@gmail.com
 *         date: 22/08/2016
 */
@Entity
@Table(name = "userprincipal", schema = "springsecurity")
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE,
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public class UserprincipalEntity implements UserDetails, CredentialsContainer, Cloneable
{
    private Long userId;
    private String username;
    private byte[] hashedPassword;
    private Set<UserprincipalAuthorityEntity> authorities = new HashSet<>();
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    @Id
    @Column(name = "UserId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    @Basic
    @Column(name = "Username", nullable = false, length = 30)
    @JsonProperty
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Basic
    @Column(name = "HashedPassword", nullable = false)
    public byte[] getHashedPassword()
    {
        return hashedPassword;
    }

    public void setHashedPassword(byte[] hashedPassword)
    {
        this.hashedPassword = hashedPassword;
    }

    @Override
    public void eraseCredentials()
    {
        this.hashedPassword = null;
    }

    @Basic
    @Column(name = "AccountNonExpired", nullable = false)
    @JsonProperty
    @Override
    public boolean isAccountNonExpired()
    {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired)
    {
        this.accountNonExpired = accountNonExpired;
    }

    @Basic
    @Column(name = "AccountNonLocked", nullable = false)
    @JsonProperty
    @Override
    public boolean isAccountNonLocked()
    {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked)
    {
        this.accountNonLocked = accountNonLocked;
    }

    @Basic
    @Column(name = "CredentialsNonExpired", nullable = false)
    @JsonProperty
    @Override
    public boolean isCredentialsNonExpired()
    {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired)
    {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Basic
    @Column(name = "Enabled", nullable = false)
    @JsonProperty
    @Override
    public boolean isEnabled()
    {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserprincipalEntity that = (UserprincipalEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (!Arrays.equals(hashedPassword, that.hashedPassword)) return false;
        if (accountNonExpired != null ? !accountNonExpired.equals(that.accountNonExpired) : that.accountNonExpired != null)
            return false;
        if (accountNonLocked != null ? !accountNonLocked.equals(that.accountNonLocked) : that.accountNonLocked != null)
            return false;
        if (credentialsNonExpired != null ? !credentialsNonExpired.equals(that.credentialsNonExpired) : that.credentialsNonExpired != null)
            return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(hashedPassword);
        result = 31 * result + (accountNonExpired != null ? accountNonExpired.hashCode() : 0);
        result = 31 * result + (accountNonLocked != null ? accountNonLocked.hashCode() : 0);
        result = 31 * result + (credentialsNonExpired != null ? credentialsNonExpired.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }


    /**
     * Clonable
     */
    @Transient
    @Override
    public String getPassword()
    {
        return this.getHashedPassword() == null ? null :
                new String(this.getHashedPassword(), StandardCharsets.UTF_8);

    }

    /**
     * UserDetail
     */
    @Override
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "userprincipal_authority", joinColumns = @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    )
    @JsonProperty
    public Set<UserprincipalAuthorityEntity> getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities(Set<UserprincipalAuthorityEntity> authorities)
    {
        this.authorities = authorities;
    }


}
