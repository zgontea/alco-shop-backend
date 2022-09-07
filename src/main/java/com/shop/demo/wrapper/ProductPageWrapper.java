package com.shop.demo.wrapper;

import com.shop.demo.model.Product;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class ProductPageWrapper
{
    @NotNull
    List<Product> products;
    @NotNull
    int totalPages;
}
