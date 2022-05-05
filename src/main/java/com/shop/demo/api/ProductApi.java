package com.shop.demo.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import com.shop.demo.model.Category;
import com.shop.demo.model.Product;
import com.shop.demo.service.CategoryManager;
import com.shop.demo.service.ProductManager;

import com.shop.demo.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductApi {

    private final ProductManager productManager;
	private final CategoryManager categoryManager;

	@Autowired
	public ProductApi(ProductManager productManager, CategoryManager categoryManager) {
		super();
		this.productManager = productManager;
		this.categoryManager = categoryManager;
	}

	@GetMapping("/all")
	public Iterable<Product> getAll() {
		return productManager.findAll();
	}

	@GetMapping("/id")
	public Optional<Product> getById(@RequestParam Long index) {
		return productManager.findById(index);
	}

	@GetMapping(value = "/{productId}")
	public Optional<Product> getId(@PathVariable("productId") Long productId) {
		return productManager.findById(productId);
	}

	@PostMapping("/save")
	public Product add(@RequestBody Product product) {
		return productManager.save(product);
	}

	@PutMapping("/upd")
	public Product update(@RequestBody Product product) {
		return productManager.save(product);
	}

	@DeleteMapping("/del")
	public void delete(@RequestParam Long index) {
		productManager.deleteById(index);
	}

	@PostMapping
	public Product addProduct(@RequestBody ProductWrapper productWrapper)
	{
		Category category = categoryManager.findById(productWrapper.getCategoryId())
				.orElseThrow(IllegalArgumentException::new);

		String image = saveImage(productWrapper.getImage());
		Product product = Product.builder()
				.name(productWrapper.getName())
				.unitPrice(productWrapper.getUnitPrice())
				.image(image)
				.build();
		category.getProducts().add(product);
		return productManager.save(product);
	}

	private String saveImage(String imageValue)
	{
		try
		{
			String base64Image = imageValue.split(",")[1];
			byte[] imageBytes = Base64.getDecoder().decode(base64Image);
			Path root = Path.of("./images");
			createDirIfNotExists(root);

			String filename = UUID.randomUUID() + ".jpg";
			new FileOutputStream(root.resolve(filename).toString()).write(imageBytes);
			return filename;
		}
		catch (IOException e)
		{
			throw new IllegalStateException("Failed to save image");
		}
	}

	private void createDirIfNotExists(Path path)
	{
		if (!path.toFile().exists())
		{
			path.toFile().mkdirs();
		}
	}
}
