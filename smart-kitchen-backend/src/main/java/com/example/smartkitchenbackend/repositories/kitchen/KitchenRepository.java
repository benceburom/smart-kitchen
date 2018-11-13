package com.example.smartkitchenbackend.repositories.kitchen;

import com.example.smartkitchenbackend.entities.Kitchen;

import java.util.List;

public interface KitchenRepository {
	Kitchen findById(long id);

	Kitchen save(Kitchen kitchen);

	void delete(Kitchen kitchen);

	List<Kitchen> findAll();

	int numberOfKitchensWithSimilarNames(String name);
}
