package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.DTOs.Food.FoodDTO;
import com.example.smartkitchenbackend.DTOs.Food.FoodDetailDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.entities.Food;
import com.example.smartkitchenbackend.repositories.food.FoodRepository;
import com.example.smartkitchenbackend.services.Converters.FoodConverter;
import com.example.smartkitchenbackend.services.Converters.IngredientConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {
	private final FoodRepository foodRepository;
	private final KitchenService kitchenService;
	private final IngredientService ingredientService;


	public FoodDTO create(FoodDTO foodDTO) {
		Food food = new Food();
		food.setKitchen(kitchenService.findById(foodDTO.getKitchenId()));
		food.setName(foodDTO.getName());
		foodRepository.save(food);
		food.setIngredients(foodDTO.getIngredients()
				.stream()
				.map(newIngredientDTO -> ingredientService.createInFood(newIngredientDTO, food))
				.collect(Collectors.toList()));
		return FoodConverter.toFoodDTO(foodRepository.save(food), foodDTO.getIngredients());
	}

	public List<FoodDetailDTO> getFoodsByKitchenId(long kitchenId) {
		return kitchenService.findById(kitchenId).getFoods().stream()
				.map(food -> FoodConverter.toFoodDetailDTO(food, convertIngredientInFood(food)))
				.collect(Collectors.toList());
	}

	public FoodDetailDTO getFoodDetails(long foodId) {
		Food food = foodRepository.findById(foodId);
		return FoodConverter.toFoodDetailDTO(food, convertIngredientInFood(food));
	}

	private List<IngredientDTO> convertIngredientInFood(Food food) {
		return food.getIngredients().stream()
				.map(neededIngredient -> IngredientConverter.toIngredientDTO(neededIngredient.getWeightOrCount(), neededIngredient.getIngredient().getName(), neededIngredient.getId()))
				.collect(Collectors.toList());
	}

	public List<FoodDetailDTO> getMakeableFoodsInKitchen(long kitchenId) {
		List<FoodDetailDTO> foods = getFoodsByKitchenId(kitchenId);
		List<FoodDetailDTO> makeableFoods = new ArrayList<>();
		Set<IngredientDTO> ingredientsInKitchen = kitchenService.findById(kitchenId).getIngredients()
				.stream()
				.map(ingredientInKitchen -> IngredientConverter.toIngredientDTO(ingredientInKitchen.getWeightOrCount(), ingredientInKitchen.getIngredient().getName(), ingredientInKitchen.getId()))
				.collect(Collectors.toSet());

		foods.forEach(foodDetailDTO -> {
					if (filtering(ingredientsInKitchen, foodDetailDTO)) makeableFoods.add(foodDetailDTO);
				}
		);
		return makeableFoods;
	}

	private boolean filtering(Set<IngredientDTO> ingredientsInKitchen, FoodDetailDTO foodDetailDTO) {
		final int[] numberOfIngredientsFound = {0};
		foodDetailDTO.getIngredients().forEach(ingredientDTO -> {
			ingredientsInKitchen.forEach(ingredient -> {
				if (ingredient.getName().equals(ingredientDTO.getName()) && ingredient.getWeightOrCount() >= ingredientDTO.getWeightOrCount())
					numberOfIngredientsFound[0]++;
			});
		});
		return numberOfIngredientsFound[0] == foodDetailDTO.getIngredients().size();
	}
}
