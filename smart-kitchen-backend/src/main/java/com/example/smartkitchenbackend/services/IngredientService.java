package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.DTOs.NeededIngredientDTO;
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


	public NeededIngredient createInFood(NeededIngredientDTO neededIngredientDTO, Food food) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(neededIngredientDTO.getName());
		ingredientRepository.save(ingredient);
		NeededIngredient neededIngredient = new NeededIngredient();
		neededIngredient.setIngredient(ingredient);
		neededIngredient.setWeightOrCount(neededIngredientDTO.getWeightOrCount());
		neededIngredient.setFood(food);
		return neededIngredientRepository.save(neededIngredient);
	}

	public void createInKitchen(String name, long weightOrCount, Kitchen kitchen) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(name);
		IngredientInKitchen ingredientInKitchen = new IngredientInKitchen();
		ingredientInKitchen.setIngredient(ingredient);
		ingredientInKitchen.setWeightOrCount(weightOrCount);
		ingredientInKitchen.setKitchen(kitchen);
		ingredientRepository.save(ingredient);
		ingredientInKitchenRepository.save(ingredientInKitchen);
	}

	public void createInWishList(String name, long weightOrCount, WishList wishList) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(name);
		WishedIngredient wishedIngredient = new WishedIngredient();
		wishedIngredient.setIngredient(ingredient);
		wishedIngredient.setWeightOrCount(weightOrCount);
		wishedIngredient.setWishList(wishList);
		ingredientRepository.save(ingredient);
		wishedIngredientRepository.save(wishedIngredient);
	}
}
