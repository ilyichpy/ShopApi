package com.example.shopapi.controller;

import com.example.shopapi.model.Product;
import com.example.shopapi.response.ProductResponse;
import com.example.shopapi.service.ImageService;
import com.example.shopapi.service.ProductService;
import com.example.shopapi.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ProductController {

	private final ProductService productService;
	private final SupplierService supplierService;
	private final ImageService imageService;

	@PostMapping("saveProduct")
	public ProductResponse saveProductInDB(@RequestBody Product product) {
		if (supplierService.findSupplierById(product.getSupplier().getId()) == null) {
			if (!supplierService.saveSupplier(product.getSupplier())) {
				return ProductResponse.builder()
						.message("Not enough info for supplier")
						.key(404)
						.product(product)
						.build();
			}
		}
		if (imageService.findImageById(product.getImageID().getId()) == null) {
			if (!imageService.saveImageInDB(product.getImageID())) {
				return ProductResponse.builder()
						.message("Fill image info")
						.key(404)
						.product(product)
						.build();
			}
		}
		product.setSupplier(supplierService.findSupplierById(product.getSupplier().getId()));
		product.setLastUpdateDate(new Date());
		if (!productService.saveProductInDB(product)) {
			return ProductResponse.builder()
					.message("Some empty fields in product")
					.product(product)
					.key(404)
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
					.key(404)
					.product(null)
					.build();
		}
		return ProductResponse.builder()
				.message("Data successfully changed")
				.key(200)
				.product(productService.findClientById(product.getId()))
				.build();
	}

	@GetMapping("findById")
	public Product findById(@RequestBody Product product) {
		return productService.findClientById(product.getId());
	}

	@GetMapping("allProducts")
	public List<Product> findAllProducts() {
		return productService.findAllProducts();
	}

	public void deleteProduct(@RequestBody Product product) {
		productService.deleteProductById(product.getId());
	}

}
