package com.example.shopapi.service;

import com.example.shopapi.model.Client;
import com.example.shopapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;

	public void saveClient(Client c) {
		clientRepository.save(c);
	}

	public Client deleteClient(UUID id) {
		Client c = clientRepository.findById(id).get();
		clientRepository.deleteById(id);
		return c;
	}

	public Client findByNameAndSurname(String name, String surname) {
		return clientRepository.findByClientNameAndClientSurname(name, surname).get();
	}
}
