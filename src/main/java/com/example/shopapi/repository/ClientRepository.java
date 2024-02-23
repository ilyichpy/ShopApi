package com.example.shopapi.repository;

import com.example.shopapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
	Optional<Client> findByClientNameAndClientSurname(String name, String surname);
}
