package com.example.shopapi.controller;

import com.example.shopapi.model.Address;
import com.example.shopapi.response.AddressResponse;
import com.example.shopapi.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class AddressController {

	private final AddressService addressService;

	@PostMapping("addAddress")
	public AddressResponse saveAddress(@RequestBody Address a) {
		if (a.isEmpty()) {
			return AddressResponse.builder()
					.message("Empty parameters")
					.key(501)
					.address(null)
					.build();
		}
		addressService.addAddress(a);
		return AddressResponse.builder()
				.message("Successfully add address")
				.address(a)
				.key(200)
				.build();
	}
}
