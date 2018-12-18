package cz.senslog.provider.security;

import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by OK on 11/17/2017.
 */
public class UserToken implements UserDetails {

    public ObjectId uid;
    private String username;
    public String email;
    private String password;
    public Collection<? extends GrantedAuthority> authorities;

    public UserToken(){}

    public UserToken(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    /* --- Collaborates --- */

    /* --- Getters / Setters --- */


    public ObjectId getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUid(ObjectId uid) {
        this.uid = uid;
    }

    public void setUserName(String name) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /* --- Commons  --- */

    @Override
    public String
    toString() {
        return "UserToken{" +
                "uid=" + uid +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}


