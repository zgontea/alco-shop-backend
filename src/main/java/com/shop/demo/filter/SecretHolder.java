package com.shop.demo.filter;

import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;

public interface SecretHolder {
    @Value("${security.jwt.secret:secret}")
    String jwtSecret = new SecureRandom().toString();
}
