package com.example.shopapi.controller;

import com.example.shopapi.model.Client;
import com.example.shopapi.model.Product;
import com.example.shopapi.model.Supplier;
import com.example.shopapi.response.ProductResponse;
import com.example.shopapi.service.ClientService;
import com.example.shopapi.service.ImageService;
import com.example.shopapi.service.ProductService;
import com.example.shopapi.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
						.key(501)
						.product(product)
						.build();
			}
		}
		if (imageService.findImageById(product.getImageID().getId()) == null) {
			if (!imageService.saveImageInDB(product.getImageID())) {
				return ProductResponse.builder()
						.message("Fill image info")
						.key(501)
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
					.key(501)
					.build();
		}
		return ProductResponse.builder()
				.message("Successfully add product")
				.product(product)
				.key(200)
				.build();
	}

}
