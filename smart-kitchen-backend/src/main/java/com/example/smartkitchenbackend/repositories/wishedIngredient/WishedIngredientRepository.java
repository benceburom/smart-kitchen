package com.example.smartkitchenbackend.repositories.wishedIngredient;

import com.example.smartkitchenbackend.entities.WishedIngredient;

public interface WishedIngredientRepository {
	WishedIngredient findById(long id);

	WishedIngredient save(WishedIngredient wishedIngredient);

	void delete(WishedIngredient wishedIngredient);
}
