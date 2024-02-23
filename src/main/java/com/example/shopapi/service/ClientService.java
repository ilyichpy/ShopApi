package com.example.shopapi.service;

import com.example.shopapi.model.Client;
import com.example.shopapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;

	public void saveClient(Client c) {
		clientRepository.save(c);
	}
}
