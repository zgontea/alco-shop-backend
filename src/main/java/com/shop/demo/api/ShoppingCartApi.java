package com.shop.demo.api;

import java.util.Optional;

import com.shop.demo.model.ShoppingCart;
import com.shop.demo.service.ShoppingCartManager;
import com.shop.demo.wrapper.ShoppingCartWrapper;

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

@RestController
@CrossOrigin
@RequestMapping("/api/shopping-cart")
public class ShoppingCartApi {
    @Autowired
    private ShoppingCartManager shoppingCartManager;

    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @GetMapping("/id")
    public Optional<ShoppingCart> getById(@RequestParam Long index) {
        return shoppingCartManager.findById(index);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @GetMapping(value = "/{shoppingCartId}")
    public Optional<ShoppingCart> getId(@PathVariable("shoppingCartId") Long shoppingCartId) {
        return shoppingCartManager.findById(shoppingCartId);
    }

    @Secured({ "ROLE_ADMIN" })
    @GetMapping("/all")
    public Iterable<ShoppingCart> getAll() {
        return shoppingCartManager.findAll();
    }

    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @PutMapping("/upd")
    public ShoppingCart update(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartManager.save(shoppingCart);
    }

    @Secured({ "ROLE_ADMIN" })
    @DeleteMapping(value = "/del/{shoppingCartId}")
    public void delete(@PathVariable("shoppingCartId") Long shoppingCartId) {
        shoppingCartManager.deleteById(shoppingCartId);
    }

    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @PostMapping("/save")
    public ShoppingCart addShoppingCart(@RequestBody ShoppingCartWrapper shoppingCartWrapper) {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .products(shoppingCartWrapper.getProducts())
                .build();

        return shoppingCartManager.save(shoppingCart);
    }

}
