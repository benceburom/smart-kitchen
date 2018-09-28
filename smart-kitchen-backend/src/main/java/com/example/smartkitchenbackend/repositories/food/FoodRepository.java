package com.example.smartkitchenbackend.repositories.food;

import com.example.smartkitchenbackend.entities.Food;

public interface FoodRepository {
	Food findById(long id);

	Food save(Food food);

	void delete(Food food);
}
