package com.example.shopapi.model;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "supplier")
@Entity
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address addressId;

	private String phoneNUmber;

}
