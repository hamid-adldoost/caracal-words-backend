package com.adldoost.caracallanguage.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthentication implements Authentication {

    private final IAMClaims claims;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (String role : this.claims.getRoles()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        }
        for (String permission : this.claims.getPermissions()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public LoginResultModel getCredentials() {
        return new LoginResultModel().fromIamClaims(claims);
    }

    @Override
    public LoginResultModel getDetails() {
        return new LoginResultModel().fromIamClaims(claims);
    }

    @Override
    public Object getPrincipal() {
        return claims.getSubject();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return claims.getSubject();
    }
}
