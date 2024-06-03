package com.study.spring.base.authentication.domain.services.impl;

import com.study.spring.base.authentication.domain.models.entities.UserEntity;
import com.study.spring.base.authentication.domain.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = Logger.getLogger(TokenServiceImpl.class.getName());

    @Value("${api.security.jwt.secret}")
    private String secretKey;

    @Value("${api.security.jwt.expiration}")
    private Long expirationTime;

    @Value("${api.security.jwt.issuer}")
    private String issuer;

    @Override
    public String generateToken(UserEntity user) {
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name()) // Supondo que role seja uma String única
                .setExpiration(getExpirationDate())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        var claim = extractAllClaims(token);
        if (claim.isEmpty()) {
            logger.warning("Invalid token: unable to extract claims");
            return false;
        }
        return isValidClaim(claim.get());
    }

    @Override
    public Optional<String> extractToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return Optional.of(token.substring(7));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> extractUsername(String token) {
        var claim = extractAllClaims(token);
        if (claim.isEmpty()) {
            logger.warning("Invalid token: unable to extract username");
            return Optional.empty();
        }
        return Optional.ofNullable(claim.get().getSubject());
    }

    @Override
    public Set<SimpleGrantedAuthority> extractRoles(String token) {
        var claim = extractAllClaims(token);
        if (claim.isEmpty()) {
            logger.warning("Invalid token: unable to extract roles");
            return Collections.emptySet();
        }

        var role = claim.get().get("role", String.class);
        if (role != null) {
            return Set.of(new SimpleGrantedAuthority(role));
        }
        return Collections.emptySet();
    }

    private boolean isValidClaim(Claims claim) {
        return claim.getSubject() != null && !claim.getSubject().isEmpty()
                && claim.getIssuer().equals(this.issuer)
                && claim.getExpiration().after(new Date());
    }

    private Optional<Claims> extractAllClaims(String token) {
        try {
            return Optional.of(
                    Jwts.parserBuilder()
                            .setSigningKey(getSignInKey())
                            .build()
                            .parseClaimsJws(token)
                            .getBody()
            );
        } catch (Exception ex) {
            logger.warning("Token parsing failed: " + ex.getMessage());
            return Optional.empty();
        }
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + expirationTime);
    }

}
