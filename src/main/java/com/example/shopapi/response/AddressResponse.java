package com.example.shopapi.response;

import com.example.shopapi.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AddressResponse {

	private String message;
	private Address address;
	private int key;
}
