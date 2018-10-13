package com.example.smartkitchenbackend.endpoints;

import com.example.smartkitchenbackend.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientEndpoint {
	private final IngredientService ingredientService;

	/*@PostMapping("/create")
	public void createIngredient(@RequestBody NeededIngredientDTO neededIngredientDTO) {
		ingredientService.createInFood(neededIngredientDTO);
	}*/

}
