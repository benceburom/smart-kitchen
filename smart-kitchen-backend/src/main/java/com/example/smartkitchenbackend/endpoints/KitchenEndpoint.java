package com.example.smartkitchenbackend.endpoints;

import com.example.smartkitchenbackend.DTOs.NewKitchenDTO;
import com.example.smartkitchenbackend.services.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kitchen")
@RequiredArgsConstructor
public class KitchenEndpoint {
	private final KitchenService kitchenService;

	@PostMapping("/create")
	public void createKitchen(@RequestBody NewKitchenDTO kitchenDTO) {
		kitchenService.create(kitchenDTO);
	}
}
