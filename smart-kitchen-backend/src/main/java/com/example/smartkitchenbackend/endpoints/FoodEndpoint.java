package com.example.smartkitchenbackend.endpoints;

import com.example.smartkitchenbackend.DTOs.FoodDTO;
import com.example.smartkitchenbackend.services.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodEndpoint {
	private final FoodService foodService;

	@PostMapping("/createFood")
	public FoodDTO createFood(@RequestBody FoodDTO foodDTO) {
		return foodService.create(foodDTO);
	}
}
