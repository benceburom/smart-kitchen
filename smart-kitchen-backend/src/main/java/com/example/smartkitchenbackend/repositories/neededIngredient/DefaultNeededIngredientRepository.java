package com.example.smartkitchenbackend.repositories.neededIngredient;

import com.example.smartkitchenbackend.entities.NeededIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Repository
public class DefaultNeededIngredientRepository implements NeededIngredientRepository {
	private final NeededIngredientJPARepository neededIngredientJPARepository;

	@Override
	public NeededIngredient findById(long id) {
		return neededIngredientJPARepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public NeededIngredient save(NeededIngredient neededIngredient) {
		return neededIngredientJPARepository.save(neededIngredient);
	}

	@Override
	public void delete(NeededIngredient neededIngredient) {
		neededIngredientJPARepository.delete(neededIngredient);
	}
}
