package com.example.shopapi.repository;

import com.example.shopapi.model.Client;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID>, PagingAndSortingRepository<Client, UUID> {
	Optional<Client> findByClientNameAndClientSurname(String name, String surname);

	@Override
	Page<Client> findAll(Pageable pageable);
}
