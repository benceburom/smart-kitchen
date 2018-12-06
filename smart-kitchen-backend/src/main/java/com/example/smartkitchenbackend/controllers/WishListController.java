package com.example.smartkitchenbackend.controllers;

import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.services.IngredientService;
import com.example.smartkitchenbackend.services.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishList")
@RequiredArgsConstructor
public class WishListController {
	private final WishListService wishListService;
	private final IngredientService ingredientService;

	@GetMapping("/listIngredients/{id}")
	public List<IngredientDTO> getIngredients(@PathVariable("id") long id) {
		return wishListService.findAllIngredientsByWishListId(id);
	}

	@DeleteMapping("/empty/{wishListId}")
	public void emptyWishList(@PathVariable long wishListId){
		ingredientService.emptyWishList(wishListId);
	}
}
