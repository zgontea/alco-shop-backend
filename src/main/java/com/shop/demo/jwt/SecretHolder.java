package com.shop.demo.jwt;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.impl.TextCodec;

public class SecretHolder {
    @Value("${security.jwt.secret:secret}")
    private static final String jwtSecret = new SecureRandom().toString();

    public static String getEncodedSecret() {
        return TextCodec.BASE64.encode(jwtSecret);
    }
}
