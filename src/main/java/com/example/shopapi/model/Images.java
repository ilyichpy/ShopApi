package com.example.shopapi.model;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "images")
@Entity
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private byte[] image;
}
