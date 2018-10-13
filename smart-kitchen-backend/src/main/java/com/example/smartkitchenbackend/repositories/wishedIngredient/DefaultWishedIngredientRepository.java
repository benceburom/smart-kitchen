package com.example.smartkitchenbackend.repositories.wishedIngredient;

import com.example.smartkitchenbackend.entities.WishedIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Repository
public class DefaultWishedIngredientRepository implements WishedIngredientRepository {
	private final WishedIngredientJPARepository wishedIngredientJPARepository;

	@Override
	public WishedIngredient findById(long id) {
		return wishedIngredientJPARepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public WishedIngredient save(WishedIngredient wishedIngredient) {
		return wishedIngredientJPARepository.save(wishedIngredient);
	}

	@Override
	public void delete(WishedIngredient wishedIngredient) {
		wishedIngredientJPARepository.delete(wishedIngredient);
	}

}
