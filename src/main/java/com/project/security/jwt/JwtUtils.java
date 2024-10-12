package com.project.security.jwt;

import com.project.entity.concretes.user.User;
import com.project.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
    private static final int RESET_CODE_LENGTH = 6;
    private static final String RESET_CODE_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Value("${backendapi.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    // Güvenli bir anahtar oluşturma

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);



    // JWT token oluşturma
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return generateTokenFromEmail(userDetails.getEmail());
    }

    public String generateTokenFromEmail(String mail) {
        return Jwts.builder()
                .setSubject(mail)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key)  // Anahtar burada kullanılıyor
                .compact();
    }


    // JWT token doğrulama
    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)  // Anahtar burada kullanılıyor
                    .build()
                    .parseClaimsJws(jwtToken);
            return true;
        } catch (JwtException e) {
            LOGGER.error("JWT Token validation error: {}", e.getMessage());
        }
        return false;
    }

    // Token'dan kullanıcı adını alma
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)  // Anahtar burada kullanılıyor
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    // Reset kodu oluşturma
    public String generateResetCode(User user) {
        SecureRandom random = new SecureRandom();
        StringBuilder resetCode = new StringBuilder(RESET_CODE_LENGTH);

        for (int i = 0; i < RESET_CODE_LENGTH; i++) {
            int index = random.nextInt(RESET_CODE_CHARACTERS.length());
            resetCode.append(RESET_CODE_CHARACTERS.charAt(index));
        }

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("resetCode", resetCode.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key)  // Anahtar burada kullanılıyor
                .compact();
    }
}
