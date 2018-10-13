package com.example.smartkitchenbackend.repositories.ingerdientInKitchen;

import com.example.smartkitchenbackend.entities.IngredientInKitchen;

public interface IngredientInKitchenRepository {
	IngredientInKitchen findById(long id);

	IngredientInKitchen save(IngredientInKitchen ingredientInKitchen);

	void delete(IngredientInKitchen ingredientInKitchen);
}
