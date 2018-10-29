package com.example.smartkitchenbackend.services.Converters;

import com.example.smartkitchenbackend.DTOs.FoodDTO;
import com.example.smartkitchenbackend.DTOs.IngredientDTO;
import com.example.smartkitchenbackend.entities.Food;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class FoodConverter {
	public static FoodDTO toFoodDTO(Food food, List<IngredientDTO> ingredients) {
		return FoodDTO.builder()
				.name(food.getName())
				.kitchenId(food.getKitchen().getId())
				.ingredients(ingredients)
				.build();
	}
}
