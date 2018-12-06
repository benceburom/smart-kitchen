package com.example.smartkitchenbackend.repositories.ingerdientInKitchen;

import com.example.smartkitchenbackend.entities.IngredientInKitchen;

public interface IngredientInKitchenRepository {
	IngredientInKitchen findById(long id);

	IngredientInKitchen save(IngredientInKitchen ingredientInKitchen);

	Boolean existsByIngredientIdAndKitchenId(long ingredientId, long kitchenId);

	IngredientInKitchen findByIngredientIdAndKitchenId(long ingredientId, long kitchenId);

    void deleteById(long ingredientInKitchenId);
}
