package com.example.shopapi.service;

import com.example.shopapi.model.Images;
import com.example.shopapi.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

	private final ImageRepository imageRepository;

	public Images findImageById(UUID id) {
		return imageRepository.findById(id).get();
	}

	public boolean saveImageInDB(Images images) {
		if (images.notEnoughInfoForImages()) {
			return false;
		}
		imageRepository.save(images);
		return true;
	}
}
