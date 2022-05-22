package com.shop.demo.api;

import java.util.Optional;

import com.shop.demo.model.User;
import com.shop.demo.service.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserApi {

    @Autowired
    private UserManager userManager;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/all")
    public Iterable<User> getAll() {
        return userManager.findAll();
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/id")
    public Optional<User> getById(@RequestParam Long index) {
        return userManager.findById(index);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return userManager.findByEmail(email);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/save")
    public User add(@RequestBody UserWrapper userWrapper) {
        User user = new User();
        user.setAdmin(false);
        user.setEmail(userWrapper.email);
        user.setName(userWrapper.name);
        user.setPhone(userWrapper.phone);
        user.setSurname(userWrapper.surname);
        user.setPassword(userWrapper.password);
        return userManager.save(user);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/upd")
    public User update(@RequestBody User user) {
        return userManager.save(user);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(value = "/del/{userId}")
	public void delete(@PathVariable("userId") Long userId) {
		userManager.deleteById(userId);
	}

    @Data
    public static class UserWrapper {
        private String name;
        private String surname;
        private String email;
        private String password;
        private String phone;
    }
}
