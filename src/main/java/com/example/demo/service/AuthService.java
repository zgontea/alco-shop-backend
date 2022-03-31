package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entities.User;
import com.example.demo.exception.UserExistsException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
@RequiredArgsConstructor
public class AuthService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final SecureRandom secureRandom = new SecureRandom();


    public LoginResponse loginUser(LoginRequest loginRequest) {
        val authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(), loginRequest.getPassword()
                        )
                );

        val user = (CustomUserDetails) authenticate.getPrincipal();
        val token = jwtUtil.generateJwt(UserDto.of(user));

        return new LoginResponse(token);
    }

    public void registerUser(RegisterRequest registerRequest) {
        val existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserExistsException();
        }

        val user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .name(registerRequest.getFirstName())
                .surname(registerRequest.getLastName())
                .admin(false)
                .build();
        userRepository.save(user);

    }

}
