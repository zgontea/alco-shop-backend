package com.shop.demo.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.shop.demo.wrapper.RegisterWrapper;
import com.shop.demo.wrapper.UserCredencials;
import com.shop.demo.exception.UserExistsException;
import com.shop.demo.filter.SecretHolder;
import com.shop.demo.model.User;
import com.shop.demo.service.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import nonapi.io.github.classgraph.json.JSONSerializer;

@RestController
@CrossOrigin
@RequestMapping("auth")
public class AuthApi implements SecretHolder {

    private UserManager userManager;

    @Autowired
    public AuthApi(UserManager userManager) {
        super();
        this.userManager = userManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserCredencials user) {
        long currentTimeMiliis = System.currentTimeMillis();
        long expirationTime = 1000 * 60 * 30;

        Optional<User> userFromDatabase = userManager.findByEmail(user.getLogin());

        if (userFromDatabase.isEmpty()) {
            return "Invalid Email";
        }

        if (!BCrypt.checkpw(user.getPassword(), userFromDatabase.get().getPassword())) {
            return "Invalid Password";
        }

        String token = Jwts.builder()
                .setSubject(user.getLogin())
                .claim("roles", "user")
                .setIssuedAt(new Date(currentTimeMiliis))
                .setExpiration(new Date(currentTimeMiliis + expirationTime))
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(jwtSecret))
                .compact();

        Map<String, String> response = new HashMap<>();
        response.put("name", userFromDatabase.get().getName());
        response.put("surname", userFromDatabase.get().getSurname());
        response.put("access_token", token);
        response.put("is_admin", String.valueOf(userFromDatabase.get().isAdmin()));

        return JSONSerializer.serializeObject(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterWrapper registerCredentials)
    {
        Optional<User> existingUser = userManager.findByEmail(registerCredentials.getEmail());
        if (existingUser.isPresent()) {
            throw new UserExistsException();
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .email(registerCredentials.getEmail())
                .password(passwordEncoder.encode(registerCredentials.getPassword()))
                .name(registerCredentials.getName())
                .surname(registerCredentials.getSurname())
                .phone(registerCredentials.getPhone())
                .admin(false)
                .build();

        userManager.save(user);
        return ResponseEntity.ok().build();
    }
}
