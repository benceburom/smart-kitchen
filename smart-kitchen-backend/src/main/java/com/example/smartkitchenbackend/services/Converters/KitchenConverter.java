package com.example.smartkitchenbackend.services.Converters;

import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDetailDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.NewKitchenDTO;
import com.example.smartkitchenbackend.entities.Kitchen;
import com.example.smartkitchenbackend.entities.User;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class KitchenConverter {

	public static Kitchen toEntity(NewKitchenDTO kitchenDTO) {
		Kitchen kitchen = new Kitchen();
		kitchen.setName(kitchenDTO.getName());
		return kitchen;
	}

	public static NewKitchenDTO toNewKitchenDTO(Kitchen kitchen, long userId) {
		return NewKitchenDTO.builder()
				.name(kitchen.getName())
				.userId(userId)
				.build();
	}

	public static KitchenDTO toKitchenDTO(Kitchen kitchen) {
		return KitchenDTO.builder()
				.name(kitchen.getName())
				.id(kitchen.getId())
				.build();
	}

	public static KitchenDetailDTO toKitchenDetailDTO(Kitchen kitchen) {
		return KitchenDetailDTO.builder()
				.id(kitchen.getId())
				.name(kitchen.getName())
				.wishListId(kitchen.getWishList().getId())
				.ingredients(kitchen.getIngredients()
						.stream()
						.map(i -> IngredientConverter.toIngredientDTO(i.getWeightOrCount(), i.getIngredient().getName(),i.getIngredient().getType(), i.getId()))
						.collect(Collectors.toList()))
				.userIds(kitchen.getUsers()
						.stream()
						.map(User::getId)
						.collect(Collectors.toList()))
				.build();
	}
}
