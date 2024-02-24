package com.example.shopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "images")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Lob
	@Column(name = "image")
	private byte[] image;

	public boolean notEnoughInfoForImages() {
		return image.length == 0;
	}

}
