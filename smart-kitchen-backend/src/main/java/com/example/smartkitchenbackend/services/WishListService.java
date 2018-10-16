package com.example.smartkitchenbackend.services;

import com.example.smartkitchenbackend.entities.Kitchen;
import com.example.smartkitchenbackend.entities.WishList;
import com.example.smartkitchenbackend.repositories.wishlist.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
