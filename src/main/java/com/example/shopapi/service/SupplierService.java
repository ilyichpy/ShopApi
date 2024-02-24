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

	public Supplier findSupplierById(UUID id) {
		return supplierRepository.findById(id).get();
	}

	public boolean saveSupplier(Supplier s) {
		if (s.notEnoughInfoForSupplier()) {
			return false;
		}
		supplierRepository.save(s);
		return true;
	}

}
