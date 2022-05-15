package com.shop.demo.wrapper;

import com.shop.demo.model.Product;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ShoppingCartWrapper
{
    @NotBlank
    private final Long id;
    @NotNull
    private final List<Product> products;
}
