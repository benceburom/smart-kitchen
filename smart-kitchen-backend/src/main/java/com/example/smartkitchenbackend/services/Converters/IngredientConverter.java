package com.example.smartkitchenbackend.services.Converters;

import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.entities.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IngredientConverter {

	public static NeededIngredient toNeededIngredientEntity(double weightOrCount, Food food, Ingredient ingredient) {
		NeededIngredient neededIngredient = new NeededIngredient();
		neededIngredient.setFood(food);
		neededIngredient.setWeightOrCount(weightOrCount);
		neededIngredient.setIngredient(ingredient);
		return neededIngredient;
	}

	public static IngredientInKitchen toIngredientInKitchenEntity(double weightOrCount, Kitchen kitchen, Ingredient ingredient) {
		IngredientInKitchen ingredientInKitchen = new IngredientInKitchen();
		ingredientInKitchen.setWeightOrCount(weightOrCount);
		ingredientInKitchen.setKitchen(kitchen);
		ingredientInKitchen.setIngredient(ingredient);
		return ingredientInKitchen;
	}

	public static IngredientDTO toIngredientDTO(double weightOrCount, String name, long id) {
		return IngredientDTO.builder()
				.id(id)
				.name(name)
				.weightOrCount(weightOrCount)
				.build();
	}
}
