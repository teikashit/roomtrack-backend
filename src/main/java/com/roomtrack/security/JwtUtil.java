package com.roomtrack.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * Utility class for generating and validating JWT tokens.
 * The Android app sends the token as "Bearer <token>" in the Authorization header.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration; // milliseconds

    /**
     * Generate a signed JWT token for the given user ID and role.
     *
     * @param userId the user's UUID
     * @param email  the user's email
     * @param role   "landlord" or "tenant"
     * @return signed JWT string
     */
    public String generateToken(String userId, String email, String role) {
        Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));

        return Jwts.builder()
                .setSubject(userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Parse and validate the token, returning the Claims (payload).
     *
     * @param token raw JWT string (without "Bearer " prefix)
     * @return Claims object with subject, email, role
     * @throws JwtException if the token is invalid or expired
     */
    public Claims validateToken(String token) {
        Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extract the user ID (subject) from the token.
     *
     * @param token raw JWT string
     * @return user UUID string
     */
    public String getUserIdFromToken(String token) {
        return validateToken(token).getSubject();
    }

    /**
     * Extract the role claim from the token.
     *
     * @param token raw JWT string
     * @return "landlord" or "tenant"
     */
    public String getRoleFromToken(String token) {
        return validateToken(token).get("role", String.class);
    }
}
