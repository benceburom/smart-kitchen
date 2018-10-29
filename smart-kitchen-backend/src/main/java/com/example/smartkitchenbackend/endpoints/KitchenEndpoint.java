package com.example.smartkitchenbackend.endpoints;

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
public class KitchenEndpoint {
	private final KitchenService kitchenService;

	@PostMapping("/create")
	public NewKitchenDTO createKitchen(@RequestBody NewKitchenDTO kitchenDTO) {
		return kitchenService.create(kitchenDTO);
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
