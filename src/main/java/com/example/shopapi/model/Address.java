package com.example.shopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "address")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String country;

	private String city;

	private String street;

	public boolean isEmpty() {
		return this.country.isEmpty() || this.city.isEmpty() || this.street.isEmpty();
	}
}
