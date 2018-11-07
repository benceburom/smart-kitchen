package com.example.smartkitchenbackend.repositories.wishlist;

import com.example.smartkitchenbackend.entities.WishList;
import com.example.smartkitchenbackend.expection.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DefaultWishListRepository implements WishListRepository {
	private final WishListJPARepository wishListJPARepository;

	@Override
	public WishList findById(long id) {
		return wishListJPARepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No wish list exists with id:" + id));
	}

	@Override
	public WishList save(WishList wishList) {
		return wishListJPARepository.save(wishList);
	}

	@Override
	public void delete(WishList wishList) {
		wishListJPARepository.delete(wishList);
	}

	@Override
	public WishList findReference(long wishListId) {
		return wishListJPARepository.getOne(wishListId);
	}
}
