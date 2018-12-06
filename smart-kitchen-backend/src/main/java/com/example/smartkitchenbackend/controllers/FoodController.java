package com.example.smartkitchenbackend.controllers;

import com.example.smartkitchenbackend.DTOs.Food.FoodDTO;
import com.example.smartkitchenbackend.DTOs.Food.FoodDetailDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.NewIngredientDTO;
import com.example.smartkitchenbackend.services.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
	private final FoodService foodService;

	@PostMapping("/createFood")
	public void createFood(@RequestBody FoodDTO foodDTO) {
		foodService.create(foodDTO);
	}

	@GetMapping("/listFoods/{kitchenId}")
	public List<FoodDetailDTO> getFoodsByKitchenId(@PathVariable("kitchenId") long kitchenId) {
		return foodService.getFoodsByKitchenId(kitchenId);
	}

	@GetMapping("/getFoodDetail/{foodId}")
	public FoodDetailDTO getFoodDetails(@PathVariable long foodId) {
		return foodService.getFoodDetails(foodId);
	}

	@GetMapping("/getMakeAbleFoods/{kitchenId}")
	public List<FoodDetailDTO> getMakeableFoods(@PathVariable long kitchenId) {
		return foodService.getMakeableFoodsInKitchen(kitchenId);
	}

	@GetMapping("/getMissingIngredients/{foodId}")
	public List<NewIngredientDTO> getMissingIngredients(@PathVariable long foodId) {
		return foodService.getMissingIngredientsForFood(foodId);
	}
}
