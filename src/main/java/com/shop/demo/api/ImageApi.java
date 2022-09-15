package com.shop.demo.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import com.shop.demo.wrapper.ImageWrapper;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ImageApi {
    @GetMapping(value = "/images/{name}")
    @CrossOrigin(origins = { "http://25.13.125.17:4200", "http://localhost:4200" })
    public ImageWrapper getImage(@PathVariable String name) {
        File file = new File("./images/" + name);

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            String encodedImage = Base64.getEncoder().encodeToString(bytes);
            return new ImageWrapper(encodedImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
