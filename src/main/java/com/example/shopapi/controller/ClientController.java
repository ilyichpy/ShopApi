package com.example.shopapi.controller;

import com.example.shopapi.model.Address;
import com.example.shopapi.model.Client;
import com.example.shopapi.repository.AddressRepository;
import com.example.shopapi.response.ClientResponse;
import com.example.shopapi.service.AddressService;
import com.example.shopapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ClientController {

	private final ClientService clientService;
	private final AddressService addressService;

	@PostMapping("saveUser")
	public ClientResponse saveUser(@RequestBody Client client) {
		Client c = Client.builder()
				.clientName(client.getClientName())
				.clientSurname(client.getClientSurname())
				.birthday(client.getBirthday())
				.gender(client.getGender())
				.addressId(addressService.findAddressByAllArgs(client.getAddressId().getCountry(), client.getAddressId().getCity(), client.getAddressId().getStreet()))
				.registrationDate(new Date())
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
