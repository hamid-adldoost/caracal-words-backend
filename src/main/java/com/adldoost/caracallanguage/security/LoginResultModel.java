package com.adldoost.caracallanguage.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResultModel implements Serializable {

    private String username;
    private String realm;
    private Set<String> roles;
    private Set<String> permissions;

    public LoginResultModel fromIamClaims(IAMClaims claims) {
        Set<String> roles = new HashSet<>(Arrays.asList(claims.getRoles()));
        Set<String> permissions = new HashSet<>(Arrays.asList(claims.getPermissions()));
        return this
                .setRoles(roles)
                .setPermissions(permissions)
                .setUsername(claims.getSubject());
    }

}
