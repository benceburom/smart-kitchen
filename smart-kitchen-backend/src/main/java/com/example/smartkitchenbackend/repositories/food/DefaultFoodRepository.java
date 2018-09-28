package com.example.smartkitchenbackend.repositories.food;

import com.example.smartkitchenbackend.entities.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Repository
public class DefaultFoodRepository implements FoodRepository {
	private final FoodJPARepository foodJPARepository;

	@Override
	public Food findById(long id) {
		return foodJPARepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Food save(Food food) {
		return foodJPARepository.save(food);
	}

	@Override
	public void delete(Food food) {
		foodJPARepository.delete(food);
	}
}
