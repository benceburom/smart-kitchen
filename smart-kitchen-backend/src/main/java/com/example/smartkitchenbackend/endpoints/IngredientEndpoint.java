package com.example.smartkitchenbackend.endpoints;

import com.example.smartkitchenbackend.DTOs.IngredientDTO;
import com.example.smartkitchenbackend.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientEndpoint {
	private final IngredientService ingredientService;

	@PostMapping("/createInKitchen/{kitchenId}")
	public IngredientDTO createIngredientInKitchen(@RequestBody IngredientDTO ingredientDTO, @PathVariable long kitchenId) {
		return ingredientService.createInKitchen(ingredientDTO, kitchenId);
	}

	@PostMapping("createInWishList/{wishListId}")
	public IngredientDTO createIngredientInWishList(@RequestBody IngredientDTO ingredientDTO, @PathVariable long wishListId) {
		return ingredientService.createInWishList(ingredientDTO, wishListId);
	}
}
