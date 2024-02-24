package com.example.shopapi.service;

import com.example.shopapi.model.Address;
import com.example.shopapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

	private final AddressRepository addressRepository;

	public boolean saveAddressInBD(Address address) {
		if (address.notEnoughInfo()) {
			return false;
		}
		if (addressRepository.findByCountryAndCityAndStreet(address.getCountry(), address.getCity(), address.getStreet()).isPresent()) {
			return true;
		}
		addressRepository.save(address);
		return true;
	}

	public Address findAddressByAllArgs(String country, String city, String street) {
		return addressRepository.findByCountryAndCityAndStreet(country, city, street).get();
	}
}
