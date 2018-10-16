package com.example.smartkitchenbackend.repositories.ingredient;

import com.example.smartkitchenbackend.entities.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Repository
public class DefaultIngredientRepository implements IngredientRepository {
	private final IngredientJPARepository ingredientJPARepository;

	@Override
	public Ingredient findById(long id) {
		return ingredientJPARepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		return ingredientJPARepository.save(ingredient);
	}

	@Override
	public void delete(Ingredient ingredient) {
		ingredientJPARepository.delete(ingredient);
	}

	@Override
	public Boolean existsByName(String name) {
		return ingredientJPARepository.existsByName(name);
	}

	@Override
	public Ingredient findByName(String name) {
		return ingredientJPARepository.findByName(name);
	}
}
