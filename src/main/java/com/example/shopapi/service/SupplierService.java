package com.example.shopapi.service;

import com.example.shopapi.model.Supplier;
import com.example.shopapi.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierService {

	private final SupplierRepository supplierRepository;
	private final AddressService addressService;

	public Supplier findSupplierById(UUID id) {
		return supplierRepository.findById(id).orElse(null);
	}

	public boolean saveSupplier(Supplier s) {
		if (s.notEnoughInfoForSupplier()) {
			return false;
		}
		addressService.saveAddressInBD(s.getAddress());
		s.setAddress(addressService.findAddressByAllArgs(s.getAddress().getCountry(),
				s.getAddress().getCity(),
				s.getAddress().getStreet()));
		if (supplierRepository.findSupplierByNameAndPhoneNumberAndAddress(s.getName(), s.getPhoneNumber(), s.getAddress()).isPresent()) {
			return true;
		}
		supplierRepository.save(s);
		return true;
	}

}
