package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.DTOs.IngredientDTO;
import com.example.smartkitchenbackend.entities.Kitchen;
import com.example.smartkitchenbackend.entities.WishList;
import com.example.smartkitchenbackend.repositories.wishlist.WishListRepository;
import com.example.smartkitchenbackend.services.Converters.IngredientConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {
	private final WishListRepository wishListRepository;

	public WishList createWishListInKitchen(Kitchen kitchen) {
		WishList wishList = new WishList();
		wishList.setKitchen(kitchen);
		return wishListRepository.save(wishList);
	}

	public WishList findById(long wishListId) {
		return wishListRepository.findById(wishListId);
	}

	public List<IngredientDTO> findAllIngredientsByWishListId(long id) {
		return wishListRepository.findById(id).getIngredients()
				.stream()
				.map(wishedIngredient -> IngredientConverter.toIngredientDTO(wishedIngredient.getWeightOrCount(), wishedIngredient.getIngredient().getName()))
				.collect(Collectors.toList());
	}
}
