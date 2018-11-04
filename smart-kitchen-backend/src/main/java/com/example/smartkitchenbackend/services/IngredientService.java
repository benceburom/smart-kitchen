package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.NewIngredientDTO;
import com.example.smartkitchenbackend.entities.*;
import com.example.smartkitchenbackend.repositories.food.FoodRepository;
import com.example.smartkitchenbackend.repositories.ingerdientInKitchen.IngredientInKitchenRepository;
import com.example.smartkitchenbackend.repositories.ingredient.IngredientRepository;
import com.example.smartkitchenbackend.repositories.neededIngredient.NeededIngredientRepository;
import com.example.smartkitchenbackend.repositories.wishedIngredient.WishedIngredientRepository;
import com.example.smartkitchenbackend.repositories.wishlist.WishListRepository;
import com.example.smartkitchenbackend.services.Converters.IngredientConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IngredientService {
	private final WishListRepository wishListRepository;
	private final KitchenService kitchenService;
	private final FoodRepository foodRepository;
	private final IngredientRepository ingredientRepository;
	private final IngredientInKitchenRepository ingredientInKitchenRepository;
	private final NeededIngredientRepository neededIngredientRepository;
	private final WishedIngredientRepository wishedIngredientRepository;


	public NeededIngredient createInFood(NewIngredientDTO newIngredientDTO, Food food) {
		if (ingredientRepository.existsByName(newIngredientDTO.getName())) {
			Ingredient ingredient = ingredientRepository.findByName(newIngredientDTO.getName());
			return createIngredientInFood(newIngredientDTO.getWeightOrCount(), food.getId(), ingredient);
		} else {
			Ingredient ingredient = new Ingredient();
			ingredient.setName(newIngredientDTO.getName());
			ingredientRepository.save(ingredient);
			return createIngredientInFood(newIngredientDTO.getWeightOrCount(), food.getId(), ingredient);
		}

	}

	public IngredientDTO createInKitchen(IngredientDTO ingredientDTO, long kitchenId) {
		if (ingredientRepository.existsByName(ingredientDTO.getName())) {
			Ingredient ingredient = ingredientRepository.findByName(ingredientDTO.getName());
			return createIngredientInKitchen(ingredientDTO.getWeightOrCount(), kitchenId, ingredient);
		} else {
			Ingredient ingredient = new Ingredient();
			ingredient.setName(ingredientDTO.getName());
			ingredientRepository.save(ingredient);
			return createIngredientInKitchen(ingredientDTO.getWeightOrCount(), kitchenId, ingredient);
		}
	}

	public IngredientDTO createInWishList(IngredientDTO ingredientDTO, long wishListId) {
		if (ingredientRepository.existsByName(ingredientDTO.getName())) {
			Ingredient ingredient = ingredientRepository.findByName(ingredientDTO.getName());
			return createWishedIngredient(ingredientDTO.getWeightOrCount(), wishListId, ingredient);
		} else {
			Ingredient ingredient = new Ingredient();
			ingredient.setName(ingredientDTO.getName());
			ingredientRepository.save(ingredient);
			return createWishedIngredient(ingredientDTO.getWeightOrCount(), wishListId, ingredient);
		}
	}

	private IngredientDTO createIngredientInKitchen(double weightOrCount, long kitchenId, Ingredient ingredient) {
		if (ingredientInKitchenRepository.existsByIngredientIdAndKitchenId(ingredient.getId(), kitchenId)) {
			IngredientInKitchen ingredientInKitchen = ingredientInKitchenRepository.findByIngredientIdAndKitchenId(ingredient.getId(), kitchenId);
			ingredientInKitchen.setWeightOrCount(ingredientInKitchen.getWeightOrCount() + weightOrCount);
			ingredientInKitchenRepository.save(ingredientInKitchen);
			return IngredientConverter.toIngredientDTO(weightOrCount, ingredient.getName(), ingredientInKitchen.getId());
		} else {
			IngredientInKitchen ingredientInKitchen = new IngredientInKitchen();
			ingredientInKitchen.setIngredient(ingredient);
			ingredientInKitchen.setWeightOrCount(weightOrCount);
			ingredientInKitchen.setKitchen(kitchenService.findById(kitchenId));
			ingredientInKitchenRepository.save(ingredientInKitchen);
			return IngredientConverter.toIngredientDTO(weightOrCount, ingredient.getName(), ingredientInKitchen.getId());
		}
	}

	private IngredientDTO createWishedIngredient(double weightOrCount, long wishListId, Ingredient ingredient) {
		if (wishedIngredientRepository.existsByIngredientIdAndWishListId(ingredient.getId(), wishListId)) {
			WishedIngredient wishedIngredient = wishedIngredientRepository.findByIngredientIdAndWishListId(ingredient.getId(), wishListId);
			wishedIngredient.setWeightOrCount(wishedIngredient.getWeightOrCount() + weightOrCount);
			wishedIngredientRepository.save(wishedIngredient);
			return IngredientConverter.toIngredientDTO(weightOrCount, ingredient.getName(), wishedIngredient.getId());
		} else {
			WishedIngredient wishedIngredient = new WishedIngredient();
			wishedIngredient.setIngredient(ingredient);
			wishedIngredient.setWeightOrCount(weightOrCount);
			wishedIngredient.setWishList(wishListRepository.findReference(wishListId));
			wishedIngredientRepository.save(wishedIngredient);
			return IngredientConverter.toIngredientDTO(weightOrCount, ingredient.getName(), wishedIngredient.getId());
		}
	}

	private NeededIngredient createIngredientInFood(double weightOrCount, long foodId, Ingredient ingredient) {
		if (neededIngredientRepository.existsByIngredientIdAndFoodId(ingredient.getId(), foodId)) {
			NeededIngredient neededIngredient = neededIngredientRepository.findByIngredientIdAndFoodId(ingredient.getId(), foodId);
			neededIngredient.setWeightOrCount(neededIngredient.getWeightOrCount() + weightOrCount);
			neededIngredientRepository.save(neededIngredient);
			return IngredientConverter.toNeededIngredientEntity(weightOrCount, foodRepository.findReference(foodId), ingredient);
		} else {
			NeededIngredient neededIngredient = new NeededIngredient();
			neededIngredient.setIngredient(ingredient);
			neededIngredient.setWeightOrCount(weightOrCount);
			neededIngredient.setFood(foodRepository.findReference(foodId));
			neededIngredientRepository.save(neededIngredient);
			return IngredientConverter.toNeededIngredientEntity(weightOrCount, foodRepository.findReference(foodId), ingredient);
		}
	}

	public void removeFromWishList(long wishedIngredientId) {
		wishedIngredientRepository.deleteById(wishedIngredientId);
	}

	@Transactional
	public void emptyWishList(long wishListId) {
		wishListRepository.findReference(wishListId).getIngredients().forEach(wishedIngredient -> removeFromWishList(wishedIngredient.getId()));
	}
}
