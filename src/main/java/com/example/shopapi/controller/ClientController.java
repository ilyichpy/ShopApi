package com.example.shopapi.controller;

import com.example.shopapi.model.Client;
import com.example.shopapi.response.ClientResponse;
import com.example.shopapi.service.AddressService;
import com.example.shopapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ClientController {

	private final ClientService clientService;
	private final AddressService addressService;

	@PostMapping("saveUser")
	public ClientResponse saveUser(@RequestBody Client client) {
		client.setRegistrationDate(new Date());
		if (client.notEnoughInfo()) {
			return ClientResponse.builder()
					.message("Empty parameters")
					.client(null)
					.key(501)
					.build();
		}

		clientService.saveClient(client);
		return ClientResponse.builder()
				.message("Successful")
				.client(client)
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

	@GetMapping("allClients")
	public List<Client> findAll(@RequestParam(defaultValue = "0") int offset,
								@RequestParam(defaultValue = "0") int limit) {
		if (limit != 0 || offset != 0) {
			return clientService.findAllWithPagination(PageRequest.of(offset, limit));
		}
		return clientService.findAll();
	}

	@PutMapping("updateAddress")
	public ClientResponse updateUserAddress(@RequestBody Client client) {
		addressService.addAddress(client.getAddressId());
		clientService.updateClientAddress(clientService.findById(client.getId()), client.getAddressId());
		return ClientResponse.builder()
				.message("Alright")
				.client(clientService.findById(client.getId()))
				.key(200)
				.build();
	}

}
