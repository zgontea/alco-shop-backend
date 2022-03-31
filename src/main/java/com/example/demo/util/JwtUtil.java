package com.example.demo.util;

import com.example.demo.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil
{

    @Value("${security.jwt.secret:secret}")
    private String jwtSecret;
    @Value("${security.jwt.issuer:elektronik.com}")
    private String jwtIssuer;
    @Value("${security.jwt.expiration:3600}")
    private Long jwtExpiration;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateJwt(UserDto user) {
        return Jwts.builder()
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .claim("user", user)
                .compact();
    }

    public UserDto getUser(String jwt) {
        val claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwt)
                .getBody();

        return objectMapper.convertValue(claims.get("user"), UserDto.class);
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.warn("Invalid token {} - {}", token, e.getMessage());
        }
        return false;
    }
}
