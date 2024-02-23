package com.example.shopapi.controller;

import com.example.shopapi.model.Client;
import com.example.shopapi.repository.AddressRepository;
import com.example.shopapi.response.ClientResponse;
import com.example.shopapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

	@DeleteMapping("deleteUser")
	public ClientResponse deleteUser(@PathVariable UUID id) {
		Client c = clientService.deleteClient(id);
		return ClientResponse.builder()
				.message("User delete successfully")
				.client(c)
				.key(200)
				.build();
	}

	@GetMapping("findUser")
	public ClientResponse findUserByNameAndSurname(@PathVariable String name,
										 @PathVariable String surname) {
		Client c = clientService.findByNameAndSurname(name, surname);
		return ClientResponse.builder()
				.client(c)
				.message("Success")
				.key(200)
				.build();
	}

}
