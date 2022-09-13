package com.shop.demo.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

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

import com.shop.demo.model.Category;
import com.shop.demo.model.Product;
import com.shop.demo.service.CategoryManager;
import com.shop.demo.service.ProductManager;
import com.shop.demo.wrapper.ProductPageWrapper;
import com.shop.demo.wrapper.ProductWrapper;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductApi {

	@Autowired
	private ProductManager productManager;

	@Autowired
	private CategoryManager categoryManager;

	@GetMapping("/filtered")
	public ProductPageWrapper getFiltered(@RequestParam int offset, @RequestParam int itemsOnPage,
			@RequestParam(required = false) String category, @RequestParam(required = false) String productName,
			@RequestParam(required = false) BigDecimal unitPriceMin, @RequestParam(required = false) BigDecimal unitPriceMax) {
		if (unitPriceMax == null || unitPriceMax.doubleValue() == 0) unitPriceMax = BigDecimal.valueOf(Long.MAX_VALUE);
		if (unitPriceMin == null || unitPriceMin.doubleValue() == 0) unitPriceMin = BigDecimal.valueOf(0);
		if (productName == null || productName.isBlank()) productName = "";
		if (category == null) {
			return productManager.getFiltered(offset, itemsOnPage, productName, unitPriceMin, unitPriceMax);
		} else {
			return productManager.getFiltered(offset, itemsOnPage, category, productName, unitPriceMin, unitPriceMax);
		}
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

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/upd")
	public Product update(@RequestBody Product product) {
		return productManager.save(product);
	}

	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping(value = "/del/{productId}")
	public void delete(@PathVariable("productId") Long productId) {
		productManager.deleteById(productId);
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/add")
	public Product addProduct(@RequestBody ProductWrapper productWrapper) {
		System.out.println(productWrapper.getCategoryName());
		Category category = categoryManager.findByName(productWrapper.getCategoryName())
				.orElseThrow(IllegalArgumentException::new);

		String image = saveImage(productWrapper.getImage());
		Product product = Product.builder()
				.name(productWrapper.getName())
				.unitPrice(productWrapper.getUnitPrice())
				.image(image)
				.concentration(productWrapper.getConcentration())
				.size(productWrapper.getSize())
				.description(productWrapper.getDescription())
				.category(category)
				.build();
		// category.getProducts().add(product);
		return productManager.save(product);
	}

	private String saveImage(String imageValue) {
		try {
			String base64Image = imageValue.split(",")[1];
			byte[] imageBytes = Base64.getDecoder().decode(base64Image);
			Path root = Path.of("./images");
			createDirIfNotExists(root);

			String filename = UUID.randomUUID() + ".jpg";
			FileOutputStream fileOutputStream = new FileOutputStream(root.resolve(filename).toString());
			fileOutputStream.write(imageBytes);
			fileOutputStream.close();

			return filename;
		} catch (IOException e) {
			throw new IllegalStateException("Failed to save image");
		}
	}

	private void createDirIfNotExists(Path path) {
		if (!path.toFile().exists()) {
			path.toFile().mkdirs();
		}
	}
}
