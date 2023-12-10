package com.adldoost.caracallanguage.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    private final String accessTokenKey;
    public static final String XForwardedForHeader = "X-Forwarded-For";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        IAMClaims claims;
        String ip;
        try {
            claims = new IAMClaims().fromClaims(jwtUtil.getClaimsFromJwt(token, JwtUtil.getHmacSHA512(accessTokenKey.getBytes())));
            ip = getUserIp(request);
            claims.setIp(ip);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            setResponseForAccessDeniedException(response);
            return;
        }

        JwtAuthentication authentication = new JwtAuthentication(new IAMClaims().fromClaims(claims));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private static String getUserIp(HttpServletRequest request) {
        return request.getHeader(JwtAuthenticationFilter.XForwardedForHeader);
    }

    private void setResponseForAccessDeniedException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new ResponseEntity<String>(HttpStatusCode.valueOf(403))));
    }
}
