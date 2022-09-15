package com.shop.demo.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.demo.jwt.TokenProvider;
import com.shop.demo.model.User;
import com.shop.demo.service.UserManager;
import com.shop.demo.wrapper.UserCredencials;
import com.shop.demo.wrapper.UserWrapper;

import nonapi.io.github.classgraph.json.JSONSerializer;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthApi {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
	private BCryptPasswordEncoder bcryptEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredencials user) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getLogin(),
                        user.getPassword()));
                        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);

        User userFromDatabase = userManager.findByEmail(user.getLogin());

        Map<String, String> response = new HashMap<>();
        response.put("name", userFromDatabase.getName());
        response.put("surname", userFromDatabase.getSurname());
        response.put("access_token", token);
        response.put("is_admin", String.valueOf(userFromDatabase.isAdmin()));
        response.put("email", userFromDatabase.getEmail());
        response.put("user_id", Long.toString(userFromDatabase.getId()));

        return ResponseEntity.ok(JSONSerializer.serializeObject(response));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserWrapper registerCredentials) {
        User existingUser = userManager.findByEmail(registerCredentials.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(409).build();
        }

        User user = User.builder()
                .email(registerCredentials.getEmail())
                .password(bcryptEncoder.encode(registerCredentials.getPassword()))
                .name(registerCredentials.getName())
                .surname(registerCredentials.getSurname())
                .phone(registerCredentials.getPhone())
                .admin(false)
                .build();

        userManager.save(user);
        return ResponseEntity.ok().build();
    }
}
