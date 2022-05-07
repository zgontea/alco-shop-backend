package com.shop.demo.api;

import com.shop.demo.wrapper.ImageWrapper;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@RequestMapping("/api")
public class ImageApi
{
    @GetMapping("/images/{name}")
    public ImageWrapper getImage(@PathVariable String imageName) {
        File file = new File("./images" + "/" + imageName);

        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            String encodedImage = Base64.getEncoder().encodeToString(bytes);
            return new ImageWrapper(encodedImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
