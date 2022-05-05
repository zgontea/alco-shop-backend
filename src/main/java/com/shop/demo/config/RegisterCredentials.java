package com.shop.demo.config;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterCredentials
{
    @NotBlank
    private final String name;
    @NotBlank
    private final String surname;
    @NotBlank
    private final String phone;
    @Email
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;

}
