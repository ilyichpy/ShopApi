package com.example.shopapi.controller;

import com.example.shopapi.model.Client;
import com.example.shopapi.repository.AddressRepository;
import com.example.shopapi.response.ClientResponse;
import com.example.shopapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ClientController {

	private final ClientService clientService;
	private final AddressRepository addressRepository;

	@PostMapping("saveUser")
	public ClientResponse saveUser(@PathVariable String fistName,
								   @PathVariable String lastName,
								   @PathVariable String birthDate,
								   @PathVariable String gender,
								   @PathVariable UUID addressId) {
		Client c = Client.builder()
				.clientName(fistName)
				.clientSurname(lastName)
				.birthday(Date.valueOf(birthDate))
				.gender(gender)
				.addressId(addressRepository.findById(addressId).orElseThrow())
				.build();
		clientService.saveClient(c);

		return ClientResponse.builder()
						.message("Successful")
						.client(c)
						.key(200)
						.build();
	}

}
