package com.example.shopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name")
	private String name;

	@Column(name = "category")
	private String category;

	@Column(name = "available_stock")
	private long availableStock;

	@Column(name = "last_update_date")
	private Date lastUpdateDate;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Supplier supplier;

	@ManyToOne
	@JoinColumn(name = "image_id")
	private Images imageID;

	public boolean notEnoughInfoForProduct() {
		return this.name.isEmpty() || this.category.isEmpty() || this.supplier.notEnoughInfoForSupplier();
	}

}
