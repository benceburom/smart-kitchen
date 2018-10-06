package com.example.smartkitchenbackend.services.Converters;

import com.example.smartkitchenbackend.DTOs.NewKitchenDTO;
import com.example.smartkitchenbackend.entities.Kitchen;
import lombok.experimental.UtilityClass;

@UtilityClass
public class KitchenConverter {

	public static Kitchen toEntity(NewKitchenDTO kitchenDTO) {
		Kitchen kitchen = new Kitchen();
		kitchen.setName(kitchenDTO.getName());
		return kitchen;
	}

	public static NewKitchenDTO toDTO(Kitchen kitchen) {
		return NewKitchenDTO.builder()
				.name(kitchen.getName())
				.userId(kitchen.getUsers().stream().findFirst().get().getId())
				.build();
	}
}
