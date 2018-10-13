package com.example.smartkitchenbackend.repositories.neededIngredient;

import com.example.smartkitchenbackend.entities.NeededIngredient;

public interface NeededIngredientRepository {
	NeededIngredient findById(long id);

	NeededIngredient save(NeededIngredient neededIngredient);

	void delete(NeededIngredient neededIngredient);
}
