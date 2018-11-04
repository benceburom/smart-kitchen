package com.example.smartkitchenbackend.repositories.wishedIngredient;

import com.example.smartkitchenbackend.entities.WishedIngredient;

public interface WishedIngredientRepository {
	WishedIngredient findById(long id);

	WishedIngredient save(WishedIngredient wishedIngredient);

	void delete(WishedIngredient wishedIngredient);

	Boolean existsByIngredientIdAndWishListId(long id, long wishListId);

	WishedIngredient findByIngredientIdAndWishListId(long id, long wishListId);

	void deleteById(long wishedIngredientId);
}
