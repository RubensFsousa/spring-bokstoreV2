package com.study.spring.base.authentication.domain.services;

import com.study.spring.base.authentication.domain.models.entities.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Optional;
import java.util.Set;

public interface TokenService {
    String generateToken(UserEntity user);

    boolean validateToken(String token);

    Optional<String> extractToken(HttpServletRequest request);

    Optional<String> extractUsername(String token);

    Set<SimpleGrantedAuthority> extractRoles(String token);
}
