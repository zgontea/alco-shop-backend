package com.shop.demo.api;

import com.shop.demo.model.Product;
import com.shop.demo.model.ShoppingCart;
import com.shop.demo.service.ShoppingCartManager;
import com.shop.demo.wrapper.ShoppingCartWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/shopping-cart")
public class ShoppingCartApi
{
    private final ShoppingCartManager shoppingCartManager;

    @Autowired
    public ShoppingCartApi(ShoppingCartManager shoppingCartManager)
    {
        super();
        this.shoppingCartManager = shoppingCartManager;
    }

    @GetMapping("/id")
    public Optional<ShoppingCart> getById(@RequestParam Long index)
    {
        return shoppingCartManager.findById(index);
    }
    @GetMapping(value = "/{shoppingCartId}")
    public Optional<ShoppingCart> getId(@PathVariable("shoppingCartId") Long shoppingCartId) {
        return shoppingCartManager.findById(shoppingCartId);
    }
    @GetMapping("/all")
    public Iterable<ShoppingCart> getAll() {
        return shoppingCartManager.findAll();
    }

    @PutMapping("/upd")
    public ShoppingCart update(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartManager.save(shoppingCart);
    }

    @DeleteMapping(value = "/del/{shoppingCartId}")
    public void delete(@PathVariable("shoppingCartId") Long shoppingCartId) {
        shoppingCartManager.deleteById(shoppingCartId);
    }

    @PostMapping("/save")
    public ShoppingCart addShoppingCart(@RequestBody ShoppingCartWrapper shoppingCartWrapper)
    {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .products(shoppingCartWrapper.getProducts())
                .build();

        return shoppingCartManager.save(shoppingCart);
    }

}
