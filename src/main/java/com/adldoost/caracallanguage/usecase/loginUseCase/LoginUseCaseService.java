package com.adldoost.caracallanguage.usecase.loginUseCase;

import com.adldoost.caracallanguage.model.User;
import com.adldoost.caracallanguage.repository.UserRepository;
import com.adldoost.caracallanguage.security.IAMClaims;
import com.adldoost.caracallanguage.security.JwtUtil;
import com.adldoost.caracallanguage.usecase.UseCase;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginUseCaseService implements UseCase<LoginUseCaseRequest, LoginUseCaseResponse> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Override
    public LoginUseCaseResponse execute(LoginUseCaseRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            user.map(u -> {
                if (passwordEncoder.matches(request.getUsername() + request.getPassword(), u.getPassword())) {
                    return u;
                } else {
                    throw new AccessDeniedException("incorrect password");
                }
            });
        }
        return generateTokenForUser(user.get());
    }

    private LoginUseCaseResponse generateTokenForUser(User user) {
        Claims claims = new IAMClaims()
                .setRoles(new String[]{"user"})
                .setPermissions(new String[]{"user-perm"})
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setSubject(user.getUsername())
                .setIssuer("iam")
                .setNotBefore(new Date())
                .setExpiration(generateExpirationDate());
        return new LoginUseCaseResponse()
                .setToken(jwtUtil.generateIAMJwt(JwtUtil.getHmacSHA512(secretKey.getBytes()), claims));
    }

    private Date generateExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 120);
        return calendar.getTime();
    }
}
