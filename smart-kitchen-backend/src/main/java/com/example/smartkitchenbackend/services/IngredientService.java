package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.DTOs.IngredientDTO;
import com.example.smartkitchenbackend.entities.*;
import com.example.smartkitchenbackend.repositories.ingerdientInKitchen.IngredientInKitchenRepository;
import com.example.smartkitchenbackend.repositories.ingredient.IngredientRepository;
import com.example.smartkitchenbackend.repositories.neededIngredient.NeededIngredientRepository;
import com.example.smartkitchenbackend.repositories.wishedIngredient.WishedIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {
	private final IngredientRepository ingredientRepository;
	private final IngredientInKitchenRepository ingredientInKitchenRepository;
	private final NeededIngredientRepository neededIngredientRepository;
	private final WishedIngredientRepository wishedIngredientRepository;


	public NeededIngredient createInFood(IngredientDTO ingredientDTO, Food food) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(ingredientDTO.getName());
		ingredientRepository.save(ingredient);
		NeededIngredient neededIngredient = new NeededIngredient();
		neededIngredient.setIngredient(ingredient);
		neededIngredient.setWeightOrCount(ingredientDTO.getWeightOrCount());
		neededIngredient.setFood(food);
		return neededIngredientRepository.save(neededIngredient);
	}

	public void createInKitchen(IngredientDTO ingredientDTO, Kitchen kitchen) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(ingredientDTO.getName());
		IngredientInKitchen ingredientInKitchen = new IngredientInKitchen();
		ingredientInKitchen.setIngredient(ingredient);
		ingredientInKitchen.setWeightOrCount(ingredientDTO.getWeightOrCount());
		ingredientInKitchen.setKitchen(kitchen);
		ingredientRepository.save(ingredient);
		ingredientInKitchenRepository.save(ingredientInKitchen);
	}

	public void createInWishList(IngredientDTO ingredientDTO, WishList wishList) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(ingredientDTO.getName());
		WishedIngredient wishedIngredient = new WishedIngredient();
		wishedIngredient.setIngredient(ingredient);
		wishedIngredient.setWeightOrCount(ingredientDTO.getWeightOrCount());
		wishedIngredient.setWishList(wishList);
		ingredientRepository.save(ingredient);
		wishedIngredientRepository.save(wishedIngredient);
	}
}
