package com.example.smartkitchenbackend.endpoints;

import com.example.smartkitchenbackend.DTOs.Food.FoodDTO;
import com.example.smartkitchenbackend.DTOs.Food.FoodDetailDTO;
import com.example.smartkitchenbackend.services.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodEndpoint {
	private final FoodService foodService;

	@PostMapping("/createFood")
	public FoodDTO createFood(@RequestBody FoodDTO foodDTO) {
		return foodService.create(foodDTO);
	}

	@GetMapping("/listFoods/{kitchenId}")
	public List<FoodDetailDTO> getFoodsByKitchenId(@PathVariable("kitchenId") long kitchenId) {
		return foodService.getFoodsByKitchenId(kitchenId);
	}

	@GetMapping("/getFoodDetail/{foodId}")
	public FoodDetailDTO getFoodDetails(@PathVariable long foodId) {
		return foodService.getFoodDetails(foodId);
	}

	@GetMapping("/getMakeAbleFoods/{kitchenId}")
	public List<FoodDetailDTO> getMakeableFoods(@PathVariable long kitchenId) {
		return foodService.getMakeableFoodsInKitchen(kitchenId);
	}
}
