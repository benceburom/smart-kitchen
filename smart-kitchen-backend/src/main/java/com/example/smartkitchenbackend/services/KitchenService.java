package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDetailDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.NewKitchenDTO;
import com.example.smartkitchenbackend.entities.Kitchen;
import com.example.smartkitchenbackend.repositories.kitchen.KitchenRepository;
import com.example.smartkitchenbackend.repositories.user.UserRepository;
import com.example.smartkitchenbackend.services.Converters.KitchenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KitchenService {
	private final KitchenRepository kitchenRepository;
	private final UserRepository userRepository;

	public void create(NewKitchenDTO kitchenDTO) {
		Kitchen kitchen = new Kitchen();
		kitchen.setName(kitchenDTO.getName());
		kitchen.addUser(userRepository.getOne(kitchenDTO.getUserId()));
		kitchenRepository.save(kitchen);
	}

	void addUserToKitchen(long kitchenId, long userId) {
		Kitchen kitchen = kitchenRepository.findById(kitchenId);
		kitchen.addUser(userRepository.getOne(userId));
		kitchenRepository.save(kitchen);
	}

	public Kitchen findById(long kitchenId) {
		return kitchenRepository.findById(kitchenId);
	}

	public List<KitchenDTO> getKitchens() {
		return kitchenRepository.findAll()
				.stream()
				.map(KitchenConverter::toKitchenDTO)
				.collect(Collectors.toList());
	}

	public KitchenDetailDTO getKitchenById(long kitchenId) {
		return KitchenConverter.toKitchenDetailDTO(kitchenRepository.findById(kitchenId));
	}
}
