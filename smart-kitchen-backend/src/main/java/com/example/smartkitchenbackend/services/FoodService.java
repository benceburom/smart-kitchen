package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.repositories.food.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodService {
	private final FoodRepository foodRepository;


}
