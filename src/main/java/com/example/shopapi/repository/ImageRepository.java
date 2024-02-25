package com.example.shopapi.repository;

import com.example.shopapi.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Images, UUID> {

	Optional<Images> findImagesByImage(byte[] image);

	@Transactional
	@Override
	Images save(Images images);
}
