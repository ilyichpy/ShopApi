package com.example.shopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "supplier")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address addressId;

	private String phoneNUmber;

	public boolean notEnoughInfoForSupplier() {
		return this.name.isEmpty() || this.addressId.notEnoughInfo() || this.phoneNUmber.isEmpty();
	}
}
