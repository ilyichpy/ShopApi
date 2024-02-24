package com.example.shopapi.service;

import com.example.shopapi.model.Address;
import com.example.shopapi.model.Client;
import com.example.shopapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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

	public List<Client> findByNameAndSurname(String name, String surname) {
		return clientRepository.findByClientNameAndClientSurname(name, surname);
	}

	public List<Client> findAllWithPagination(PageRequest p) {
		return clientRepository.findAll(p).stream().toList();
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public void updateClientAddress(Client client, Address address) {
		client.setAddress(address);
		clientRepository.save(client);
	}

	public Client findById(UUID id) {
		return clientRepository.findById(id).get();
	}
}
