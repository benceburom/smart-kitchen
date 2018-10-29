package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.DTOs.FoodDTO;
import com.example.smartkitchenbackend.entities.Food;
import com.example.smartkitchenbackend.repositories.food.FoodRepository;
import com.example.smartkitchenbackend.services.Converters.FoodConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
				.map(neededIngredientDTO -> ingredientService.createInFood(neededIngredientDTO, food))
				.collect(Collectors.toList()));
		return FoodConverter.toFoodDTO(foodRepository.save(food), foodDTO.getIngredients());
	}
}
