package com.example.shopapi.model;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "address")
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String country;

	private String city;

	private String street;
}
