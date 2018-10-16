package com.example.smartkitchenbackend.repositories.ingerdientInKitchen;

import com.example.smartkitchenbackend.entities.IngredientInKitchen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Repository
public class DefaultIngredientInKitchenRepository implements IngredientInKitchenRepository {
	private final IngredientInKitchenJPARepository ingredientInKitchenJPARepository;

	@Override
	public IngredientInKitchen findById(long id) {
		return ingredientInKitchenJPARepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public IngredientInKitchen save(IngredientInKitchen ingredientInKitchen) {
		return ingredientInKitchenJPARepository.save(ingredientInKitchen);
	}

	@Override
	public void delete(IngredientInKitchen ingredientInKitchen) {
		ingredientInKitchenJPARepository.delete(ingredientInKitchen);
	}

	@Override
	public Boolean existsByIngredientIdAndKitchenId(long ingredientId, long kitchenId) {
		return ingredientInKitchenJPARepository.existsByIngredientIdAndKitchenId(ingredientId, kitchenId);
	}

	@Override
	public IngredientInKitchen findByIngredientIdAndKitchenId(long ingredientId, long kitchenId) {
		return ingredientInKitchenJPARepository.findByIngredientIdAndKitchenId(ingredientId, kitchenId);
	}

}
