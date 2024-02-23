package com.example.shopapi.service;

import com.example.shopapi.model.Address;
import com.example.shopapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

	private final AddressRepository addressRepository;

	public void addAddress(Address address) {
		addressRepository.save(address);
	}

}
