package com.example.demo.security;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class TokenHelper {

    private String secret = "Training";
    private Long expiration = 8L;

    @Value("Authorization")
    private String AUTH_HEADER;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (UsernameNotFoundException e) {
            username = null;
        }
        return username;
    }
    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.secret)
                    .parseClaimsJws(token).getBody();
        } catch (ClaimJwtException e) {
            claims = null;
        }
        return claims;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (ExpiredJwtException  e) {
            expiration = null;
        }
        return expiration;
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        final Claims claims = getClaimsFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, generateExpirationDate());
    }

    private String generateToken(UserDetails userDetails, Date expiredOn) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", userDetails.getUsername());
        claims.put("role", userDetails.getAuthorities());
        claims.put("created", new Date(System.currentTimeMillis()));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiredOn)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Date generateExpirationDate() {
        return new Date(getCurrentTimeMillis() + this.expiration * 60 * 60 * 1000);
    }

    private long getCurrentTimeMillis() {
        return new Date().getTime();
    }

}