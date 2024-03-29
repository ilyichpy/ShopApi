package com.example.shopapi.repository;

import com.example.shopapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
	Optional<Address> findByCountryAndCityAndStreet(String country, String city, String street);
}
