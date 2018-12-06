package com.example.smartkitchenbackend.controllers;

import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.KitchenDetailDTO;
import com.example.smartkitchenbackend.DTOs.Kitchen.NewKitchenDTO;
import com.example.smartkitchenbackend.services.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitchen")
@RequiredArgsConstructor
public class KitchenController {
	private final KitchenService kitchenService;

	@PostMapping("/create")
	public void createKitchen(@RequestBody NewKitchenDTO kitchenDTO) {
		kitchenService.create(kitchenDTO);
	}

	@GetMapping("/list")
	public List<KitchenDTO> getKitchens() {
		return kitchenService.getKitchens();
	}

	@GetMapping("/kitchenDetails/{kitchenId}")
	public KitchenDetailDTO getKitchenById(@PathVariable("kitchenId") long kitchenId) {
		return kitchenService.getKitchenById(kitchenId);
	}
}
