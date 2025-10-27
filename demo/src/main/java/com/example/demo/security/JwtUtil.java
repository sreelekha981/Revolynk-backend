package com.example.demo.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
private <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
    final Claims claims = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    return claimsResolver.apply(claims);
}

public String extractEmail(String token) {
    return extractClaim(token, Claims::getSubject);
}

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +  1000L * 60 * 60 * 24 * 30 * 6))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
    try {
        Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .setAllowedClockSkewSeconds(300) // allow 5 min time diff
            .build()
            .parseClaimsJws(token);
        return true;
    } catch (io.jsonwebtoken.ExpiredJwtException e) {
        System.out.println("❌ Token expired: " + e.getMessage());
        return false;
    } catch (Exception e) {
        System.out.println("❌ Invalid token: " + e.getMessage());
        return false;
    }
}

}
