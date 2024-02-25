package com.example.shopapi.service;

import com.example.shopapi.model.Product;
import com.example.shopapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public Product findProductById(UUID id) {
		return productRepository.findById(id).orElse(null);
	}

	public boolean saveProductInDB(Product product) {
		if (product.notEnoughInfoForProduct()) {
			return false;
		}
		productRepository.save(product);
		return true;
	}

	public boolean changeAvailableStock(Product product, long valueNew) {
		if (valueNew < 0L) {
			return false;
		}
		product.setAvailableStock(valueNew);
		product.setLastUpdateDate(new Date());
		productRepository.save(product);
		return true;
	}

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public void deleteProductById(UUID id) {
		productRepository.deleteById(id);
	}
}
