package com.example.shopapi.service;

import com.example.shopapi.model.Product;
import com.example.shopapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public boolean saveProductInDB(Product product) {
		if (product.notEnoughInfoForProduct()) {
			return false;
		}
		productRepository.save(product);
		return true;
	}
}
