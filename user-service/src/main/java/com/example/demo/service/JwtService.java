
package com.example.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import jakarta.annotation.PostConstruct;

@Service
public class JwtService {

    private String jwtSecret;

    // Store generated secret locally
    private final Path secretPath =
            Paths.get("config", "jwt-secret.txt");

    // Access token expiration: 15 minutes
    private final long accessTokenExpiration =
            1000L * 60 * 15;

    // Refresh token expiration: 7 days
    private final long refreshTokenExpiration =
            1000L * 60 * 60 * 24 * 7;

    // Generate secret once or load existing secret
    @PostConstruct
    public void init() {

        try {

            if (Files.exists(secretPath)) {

                // Load existing secret
                jwtSecret = Files.readString(secretPath).trim();

            } else {

                // Generate a secure HS256 secret
                SecretKey key = Jwts.SIG.HS256.key().build();

                jwtSecret = Base64.getEncoder()
                        .encodeToString(key.getEncoded());

                // Create config directory
                Files.createDirectories(secretPath.getParent());

                // Save secret locally
                Files.writeString(secretPath, jwtSecret);
            }

        } catch (Exception e) {

            throw new IllegalStateException(
                    "Unable to initialize JWT secret", e);
        }
    }

    // Convert Base64 secret into signing key
    private SecretKey getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate access token
    public String generateToken(String username, String role) {

        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .claim("type", "ACCESS")
                .issuedAt(new Date())
                .expiration(new Date(
                        System.currentTimeMillis()
                                + accessTokenExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    // Generate refresh token
    public String generateRefreshToken(String username) {

        return Jwts.builder()
                .subject(username)
                .claim("type", "REFRESH")
                .issuedAt(new Date())
                .expiration(new Date(
                        System.currentTimeMillis()
                                + refreshTokenExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    // Extract all claims
    public Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Extract username
    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();
    }

    // Validate access token
    public boolean isAccessTokenValid(String token) {

        Claims claims = extractAllClaims(token);

        return "ACCESS".equals(
                claims.get("type", String.class));
    }

    // Validate refresh token
    public String validateRefreshToken(String token) {

        Claims claims = extractAllClaims(token);

        if (!"REFRESH".equals(
                claims.get("type", String.class))) {

            throw new IllegalArgumentException(
                    "Invalid refresh token");
        }

        return claims.getSubject();
    }
}
