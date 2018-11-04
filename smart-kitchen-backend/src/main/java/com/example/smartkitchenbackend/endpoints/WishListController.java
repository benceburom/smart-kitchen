package com.example.smartkitchenbackend.endpoints;

import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.services.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wishList")
@RequiredArgsConstructor
public class WishListController {
	private final WishListService wishListService;

	@GetMapping("/listIngredients/{id}")
	public List<IngredientDTO> getIngredients(@PathVariable("id") long id) {
		return wishListService.findAllIngredientsByWishListId(id);
	}
}
