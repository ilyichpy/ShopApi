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

	@PutMapping("addAddress")
	public AddressResponse saveAddress(@RequestBody Address a) {
		Address address = Address.builder()
				.country(a.getCountry())
				.city(a.getCity())
				.street(a.getStreet())
				.build();
		addressService.addAddress(address);
		return AddressResponse.builder()
				.message("Successfully add address")
				.address(address)
				.key(200)
				.build();
	}
}
