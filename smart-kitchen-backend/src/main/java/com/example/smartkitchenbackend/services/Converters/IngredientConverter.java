package com.example.smartkitchenbackend.services.Converters;

import com.example.smartkitchenbackend.entities.Food;
import com.example.smartkitchenbackend.entities.Ingredient;
import com.example.smartkitchenbackend.entities.NeededIngredient;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IngredientConverter {

	public static NeededIngredient toNeededIngredientEntity(double weightOrCount, Food food, Ingredient ingredient) {
		NeededIngredient neededIngredient = new NeededIngredient();
		neededIngredient.setFood(food);
		neededIngredient.setWeightOrCount(weightOrCount);
		neededIngredient.setIngredient(ingredient);
		return neededIngredient;
	}
}
