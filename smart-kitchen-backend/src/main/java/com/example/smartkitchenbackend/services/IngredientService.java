package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.DTOs.IngredientDTO;
import com.example.smartkitchenbackend.entities.Ingredient;
import com.example.smartkitchenbackend.repositories.ingredient.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {
	private final IngredientRepository ingredientRepository;


	public void create(IngredientDTO ingredientDTO) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(ingredientDTO.getName());
		ingredientRepository.save(ingredient);
	}
}
