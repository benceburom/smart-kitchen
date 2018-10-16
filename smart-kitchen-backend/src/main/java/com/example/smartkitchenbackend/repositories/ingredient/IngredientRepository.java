package com.example.smartkitchenbackend.repositories.ingredient;

import com.example.smartkitchenbackend.entities.Ingredient;

public interface IngredientRepository {
	Ingredient findById(long id);

	Ingredient save(Ingredient ingredient);

	void delete(Ingredient ingredient);

	Boolean existsByName(String name);

	Ingredient findByName(String name);
}
