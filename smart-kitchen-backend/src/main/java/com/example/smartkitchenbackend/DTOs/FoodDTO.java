package com.example.smartkitchenbackend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FoodDTO {
	private String name;
	private long kitchenId;
	private List<IngredientDTO> ingredients;
}