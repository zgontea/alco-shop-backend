package com.shop.demo.api;

import java.util.Optional;

import com.shop.demo.model.User;
import com.shop.demo.service.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.Data;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserApi {

    private UserManager userManager;

    @Autowired
	public UserApi(UserManager userManager) {
		super();
		this.userManager = userManager;
	}

    @GetMapping("/all")
    public Iterable<User> getAll() {
        return userManager.findAll();
    }

    @GetMapping("/id")
    public Optional<User> getById(@RequestParam Long index) {
        return userManager.findById(index);
    }

    @GetMapping(value = "/{userId}")
    public Optional<User> getId(@PathVariable("userId") Long userId) {
        return userManager.findById(userId);
    }

    @PostMapping("/save")
    public User add(@RequestBody UserWrapper userWrapper) {
        User user = new User();
        user.setAdmin(false);
        user.setEmail(userWrapper.email);
        user.setName(userWrapper.name);
        user.setSurname(userWrapper.surname);
        user.setPassword(userWrapper.password);
        return userManager.save(user);
    }

    @PutMapping("/upd")
    public User update(@RequestBody User user) {
        return userManager.save(user);
    }

    @DeleteMapping("/del")
    public void delete(@RequestParam Long index) {
        userManager.deleteById(index);
    }

    @Data
    public static class UserWrapper {
        private String name;
        private String surname;
        private String email;
        private String password;
    }
}
