package com.example.shopapi.response;

import com.example.shopapi.model.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

	private String message;
	private Product product;
	private int key;
}
