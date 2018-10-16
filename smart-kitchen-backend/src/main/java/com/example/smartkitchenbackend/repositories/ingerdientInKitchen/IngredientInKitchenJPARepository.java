package com.example.smartkitchenbackend.repositories.ingerdientInKitchen;

import com.example.smartkitchenbackend.entities.IngredientInKitchen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientInKitchenJPARepository extends JpaRepository<IngredientInKitchen, Long> {
	Boolean existsByIngredientIdAndKitchenId(long ingredientId, long kitchenId);

	IngredientInKitchen findByIngredientIdAndKitchenId(long ingredientId, long kitchenId);
}