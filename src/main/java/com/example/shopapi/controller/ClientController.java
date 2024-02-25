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

	@PostMapping("addClient")
	public ClientResponse saveUser(@RequestBody Client client) {
		client.setRegistrationDate(new Date());
		if (client.notEnoughInfo()) {
			return ClientResponse.builder()
					.message("Not enough info to create client")
					.client(null)
					.key(400)
					.build();
		}

		if (addressService.findAddressByAllArgs(client.getAddress().getCountry(),
				client.getAddress().getCity(),
				client.getAddress().getStreet()) == null) {
			addressService.saveAddressInBD(client.getAddress());
		} else {
			client.setAddress(addressService.findAddressByAllArgs(
					client.getAddress().getCountry(),
					client.getAddress().getCity(),
					client.getAddress().getStreet()));
		}

		clientService.saveClient(client);
		return ClientResponse.builder()
				.message("Successfully crate client")
				.client(client)
				.key(200)
				.build();
	}

	@DeleteMapping("deleteClient")
	public ClientResponse deleteUser(@RequestBody Client client) {
		Client c = clientService.deleteClient(client.getId());
		return ClientResponse.builder()
				.message("User delete successfully")
				.client(c)
				.key(200)
				.build();
	}

	@GetMapping("findClients")
	public ClientResponse findUserByNameAndSurname(@RequestBody Client client) {
		return ClientResponse.builder()
				.client(clientService.findByNameAndSurname(client.getClientName(), client.getClientSurname()))
				.message("Ok")
				.key(200)
				.build();
	}

	@GetMapping("allClients")
	public ClientResponse findAll(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "0") int limit) {
		if (limit != 0 || offset != 0) {
			return ClientResponse.builder()
					.client(clientService.findAllWithPagination(PageRequest.of(offset, limit)))
					.message("Successfully find client with pagination")
					.key(200)
					.build();
		}
		return ClientResponse.builder()
				.client(clientService.findAll())
				.message("Successfully find all clents")
				.key(200)
				.build();
	}

	@PutMapping("updateAddress")
	public ClientResponse updateUserAddress(@RequestBody Client client) {
		if (!addressService.saveAddressInBD(client.getAddress())) {
			return ClientResponse.builder()
					.message("Not enough info in address")
					.key(400)
					.client(null)
					.build();
		}
		clientService.updateClientAddress(clientService.findById(client.getId()), addressService.findAddressByAllArgs(client.getAddress().getCountry()
				, client.getAddress().getCity()
				, client.getAddress().getStreet()));
		return ClientResponse.builder()
				.message("Successfully update client's address")
				.client(clientService.findById(client.getId()))
				.key(200)
				.build();
	}

}
