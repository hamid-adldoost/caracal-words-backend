package com.adldoost.caracallanguage.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Accessors(chain = true)
public class IAMClaims extends DefaultClaims {

    public static final String ROLES = "roles";
    public static final String PERMISSIONS = "perms";

    public static final String IP = "ip";

    public String[] getRoles() {
        String roles = this.getString(IAMClaims.ROLES);
        if (StringUtils.isBlank(roles))
            return null;
        return roles.split("\\s*,\\s*");
    }

    public IAMClaims setRoles(String[] roles) {
        this.setValue(IAMClaims.ROLES, String.join(",", roles));
        return this;
    }

    public String[] getPermissions() {
        String perms = this.getString(IAMClaims.PERMISSIONS);
        if (StringUtils.isBlank(perms))
            return null;
        return perms.split("\\s*,\\s*");
    }

    public IAMClaims setPermissions(String[] permissions) {
        this.setValue(IAMClaims.PERMISSIONS, String.join(",", permissions));
        return this;
    }

    public String getIp() {
        return this.getString(IAMClaims.IP);
    }

    public IAMClaims setIp(String ip) {
        this.setValue(IAMClaims.IP, ip);
        return this;
    }

    public Map<String, Object> getClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.ID, getId());
        claims.put(Claims.AUDIENCE, getAudience());
        claims.put(Claims.EXPIRATION, getExpiration());
        claims.put(Claims.ISSUER, getIssuer());
        claims.put(Claims.SUBJECT, getSubject());
        claims.put(Claims.ISSUED_AT, getIssuedAt());
        claims.put(Claims.NOT_BEFORE, getNotBefore());
        claims.put(IAMClaims.ROLES, getRoles());
        claims.put(IAMClaims.PERMISSIONS, getPermissions());
        claims.put(IAMClaims.IP, getIp());
        return claims;
    }

    public IAMClaims fromClaims(Claims claims) {
        this.setId(claims.getId());
        this.setAudience(claims.getAudience());
        this.setExpiration(claims.getExpiration());
        this.setIssuer(claims.getIssuer());
        this.setSubject(claims.getSubject());
        this.setIssuedAt(claims.getIssuedAt());
        this.setNotBefore(claims.getNotBefore());
        this.setIp(claims.get(IAMClaims.IP, String.class));
        String roles = claims.get(IAMClaims.ROLES, String.class);
        if (StringUtils.isNoneBlank(roles)) {
            this.setRoles(StringUtils.split(roles, ","));
        }
        String permissions = claims.get(IAMClaims.PERMISSIONS, String.class);
        if (StringUtils.isNoneBlank(getPermissions())) {
            this.setPermissions(StringUtils.split(permissions, ","));
        }
        return this;
    }


}
