package com.shop.demo.api;

import java.util.Date;

import com.shop.demo.config.UserCredencials;

import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;


@RestController
@CrossOrigin
@RequestMapping("api/login")
public class LoginApi {


    @PostMapping("/logIn")
    public String login(@RequestBody UserCredencials user) {
        long currentTimeMiliis = System.currentTimeMillis();
        long expirationTime = 1000 * 60 * 30;

        String token = Jwts.builder()
            .setSubject(user.getLogin())
            .claim("roles", "user")
            .setIssuedAt(new Date(currentTimeMiliis))
            .setExpiration(new Date(currentTimeMiliis + expirationTime))
            .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(user.getPassword()))
            .compact();
        
        return token;
    }

    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }
}
