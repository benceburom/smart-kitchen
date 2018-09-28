package com.example.smartkitchenbackend.repositories.wishlist;

import com.example.smartkitchenbackend.entities.WishList;

public interface WishListRepository {
	WishList findById(long id);

	WishList save(WishList wishList);

	void delete(WishList wishList);
}
