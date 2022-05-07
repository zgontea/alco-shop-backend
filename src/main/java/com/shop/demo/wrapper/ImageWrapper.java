package com.shop.demo.wrapper;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ImageWrapper {
    @NotNull
    private final String image;
}
