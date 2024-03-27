package com.jfteam.sharedrawing.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface IJwtService {
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    private Key getSignInKey() {
        return null;
    }

    private Claims extractAllClaims(String token) {
        return null;
    }

    public String extractUsername(String token);

    public String generateToken(UserDetails userDetails);

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

    private Date extractExpiration(String token) {
        return null;
    }

    private boolean isTokenExpired(String token) {
        return false;
    }
}
