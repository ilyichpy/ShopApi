package com.example.shopapi.response;

import com.example.shopapi.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ClientResponse {

	private String message;
	private Object client;
	private int key;
}
