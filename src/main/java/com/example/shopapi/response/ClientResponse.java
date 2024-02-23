package com.example.shopapi.response;

import com.example.shopapi.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

	private String message;
	private Client client;
	private int key;
}
