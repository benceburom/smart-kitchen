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
	private final WishListService wishListService;
	private final KitchenService kitchenService;
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

	public void createInKitchen(IngredientDTO ingredientDTO, long kitchenId) {
		if (ingredientRepository.existsByName(ingredientDTO.getName())) {
			Ingredient ingredient = ingredientRepository.findByName(ingredientDTO.getName());
			createIngredientInKitchen(ingredientDTO.getWeightOrCount(), kitchenId, ingredient);
		} else {
			Ingredient ingredient = new Ingredient();
			ingredient.setName(ingredientDTO.getName());
			ingredientRepository.save(ingredient);
			createIngredientInKitchen(ingredientDTO.getWeightOrCount(), kitchenId, ingredient);
		}
	}

	public void createInWishList(IngredientDTO ingredientDTO, long wishListId) {
		if (ingredientRepository.existsByName(ingredientDTO.getName())) {
			Ingredient ingredient = ingredientRepository.findByName(ingredientDTO.getName());
			createWishedIngredient(ingredientDTO.getWeightOrCount(), wishListId, ingredient);
		} else {
			Ingredient ingredient = new Ingredient();
			ingredient.setName(ingredientDTO.getName());
			ingredientRepository.save(ingredient);
			createWishedIngredient(ingredientDTO.getWeightOrCount(), wishListId, ingredient);
		}
	}

	private void createIngredientInKitchen(double weightOrCount, long kitchenId, Ingredient ingredient) {
		if (ingredientInKitchenRepository.existsByIngredientIdAndKitchenId(ingredient.getId(), kitchenId)) {
			IngredientInKitchen ingredientInKitchen = ingredientInKitchenRepository.findByIngredientIdAndKitchenId(ingredient.getId(), kitchenId);
			ingredientInKitchen.setWeightOrCount(ingredientInKitchen.getWeightOrCount() + weightOrCount);
			ingredientInKitchenRepository.save(ingredientInKitchen);
		} else {
			IngredientInKitchen ingredientInKitchen = new IngredientInKitchen();
			ingredientInKitchen.setIngredient(ingredient);
			ingredientInKitchen.setWeightOrCount(weightOrCount);
			ingredientInKitchen.setKitchen(kitchenService.findById(kitchenId));
			ingredientInKitchenRepository.save(ingredientInKitchen);
		}
	}

	private void createWishedIngredient(double weightOrCount, long wishListId, Ingredient ingredient) {
		if (wishedIngredientRepository.existsByIngredientIdAndWishListId(ingredient.getId(), wishListId)) {
			WishedIngredient wishedIngredient = wishedIngredientRepository.findByIngredientIdAndWishListId(ingredient.getId(), wishListId);
			wishedIngredient.setWeightOrCount(wishedIngredient.getWeightOrCount() + weightOrCount);
			wishedIngredientRepository.save(wishedIngredient);
		} else {
			WishedIngredient wishedIngredient = new WishedIngredient();
			wishedIngredient.setIngredient(ingredient);
			wishedIngredient.setWeightOrCount(weightOrCount);
			wishedIngredient.setWishList(wishListService.findById(wishListId));
			wishedIngredientRepository.save(wishedIngredient);
		}

	}
}
