package com.example.shopapi.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

	private String message;
	private Object product;
	private int key;
}
