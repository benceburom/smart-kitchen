package com.example.smartkitchenbackend.repositories.wishlist;

import com.example.smartkitchenbackend.entities.WishList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Repository
public class DefaultWishListRepository implements WishListRepository {
	private final WishListJPARepository wishListJPARepository;

	@Override
	public WishList findById(long id) {
		return wishListJPARepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public WishList save(WishList wishList) {
		return wishListJPARepository.save(wishList);
	}

	@Override
	public void delete(WishList wishList) {
		wishListJPARepository.delete(wishList);
	}
}
