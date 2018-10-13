package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.DTOs.NewKitchenDTO;
import com.example.smartkitchenbackend.entities.Kitchen;
import com.example.smartkitchenbackend.repositories.kitchen.KitchenRepository;
import com.example.smartkitchenbackend.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
