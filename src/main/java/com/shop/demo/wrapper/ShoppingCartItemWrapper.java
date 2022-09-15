package com.shop.demo.wrapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.shop.demo.model.OrderDetail;

import lombok.Data;

@Data
public class ShoppingCartItemWrapper
{
    @NotBlank
    private final Long userId;
    @NotNull
    private final OrderDetail orderDetail;
}
