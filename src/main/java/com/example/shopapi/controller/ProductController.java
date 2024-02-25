package com.example.shopapi.controller;

import com.example.shopapi.model.Product;
import com.example.shopapi.response.ProductResponse;
import com.example.shopapi.service.ImageService;
import com.example.shopapi.service.ProductService;
import com.example.shopapi.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ProductController {

	private final ProductService productService;
	private final SupplierService supplierService;
	private final ImageService imageService;

	@PostMapping("saveProduct")
	public ProductResponse saveProductInDB(@RequestBody Product product,
										   @RequestParam MultipartFile file) {
		if (!supplierService.saveSupplier(product.getSupplier())) {
			return ProductResponse.builder()
					.message("Not enough info for supplier")
					.key(400)
					.product(product)
					.build();
		}

		if (!file.isEmpty()) {
			try {
				product.getImages().setImage(file.getBytes());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			if (!imageService.saveImageInDB(product.getImages())) {
				return ProductResponse.builder()
						.message("Not enough info for image")
						.key(400)
						.product(product)
						.build();
			}
		}

		product.setSupplier(supplierService.findSupplierById(product.getSupplier().getId()));
		product.setLastUpdateDate(new Date());
		if (!productService.saveProductInDB(product)) {
			return ProductResponse.builder()
					.message("Not enough info for product")
					.product(product)
					.key(400)
					.build();
		}
		return ProductResponse.builder()
				.message("Successfully add product")
				.product(product)
				.key(200)
				.build();
	}

	@PutMapping("changeStock")
	public ProductResponse changeAvailableStock(@RequestBody Product product) {
		if(!productService.changeAvailableStock(product, product.getAvailableStock())) {
			return ProductResponse.builder()
					.message("invalid stock value")
					.key(400)
					.product(null)
					.build();
		}
		return ProductResponse.builder()
				.message("Data successfully changed")
				.key(200)
				.product(productService.findProductById(product.getId()))
				.build();
	}

	@GetMapping("findById")
	public ProductResponse findById(@RequestBody Product product) {
		if (productService.findProductById(product.getId()) == null) {
			return ProductResponse.builder()
					.message("Product not found")
					.product(null)
					.key(404)
					.build();
		}
		return ProductResponse.builder()
				.message("Successfully find product")
				.product(productService.findProductById(product.getId()))
				.key(200)
				.build();
	}

	@GetMapping("allProducts")
	public ProductResponse findAllProducts() {
		if (productService.findAllProducts().isEmpty()) {
			return ProductResponse.builder()
					.message("Products not found")
					.product(null)
					.key(404)
					.build();
		}
		return ProductResponse.builder()
				.message("Successful")
				.product(productService.findAllProducts())
				.key(200)
				.build();
	}

	@DeleteMapping("deleteProduct")
	public ProductResponse deleteProduct(@RequestBody Product product) {
		Product p = productService.findProductById(product.getId());
		productService.deleteProductById(product.getId());
		return ProductResponse.builder()
				.message("Successfully delete product")
				.product(p)
				.key(200)
				.build();
	}

}
