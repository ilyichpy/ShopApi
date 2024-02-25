package com.example.shopapi.repository;

import com.example.shopapi.model.Address;
import com.example.shopapi.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
	Optional<Supplier> findSupplierByNameAndPhoneNumberAndAddress(String name, String phone, Address address);
}
