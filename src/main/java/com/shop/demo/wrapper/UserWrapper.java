package com.shop.demo.wrapper;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.shop.demo.model.Product;

@Data
public class UserWrapper {
    @NotBlank
    private final Long id;
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
