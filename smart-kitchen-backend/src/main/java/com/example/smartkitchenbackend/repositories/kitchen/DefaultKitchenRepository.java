package com.example.smartkitchenbackend.repositories.kitchen;

import com.example.smartkitchenbackend.entities.Kitchen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class DefaultKitchenRepository implements KitchenRepository {
	private final KitchenJPARepository kitchenJPARepository;

	@Override
	public Kitchen findById(long id) {
		return kitchenJPARepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Kitchen save(Kitchen kitchen) {
		return kitchenJPARepository.save(kitchen);
	}

	@Override
	public void delete(Kitchen kitchen) {
		kitchenJPARepository.delete(kitchen);
	}

	@Override
	public List<Kitchen> findAll() {
		return kitchenJPARepository.findAll();
	}
}
