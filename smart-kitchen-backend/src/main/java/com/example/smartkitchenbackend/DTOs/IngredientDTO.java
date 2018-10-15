package com.example.smartkitchenbackend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IngredientDTO {
	private String name;
	private double weightOrCount;
}
