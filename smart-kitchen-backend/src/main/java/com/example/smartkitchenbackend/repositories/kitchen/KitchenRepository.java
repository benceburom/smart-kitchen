package com.example.smartkitchenbackend.repositories.kitchen;

import com.example.smartkitchenbackend.entities.Kitchen;

public interface KitchenRepository {
	Kitchen findById(long id);

	Kitchen save(Kitchen kitchen);

	void delete(Kitchen kitchen);
}
