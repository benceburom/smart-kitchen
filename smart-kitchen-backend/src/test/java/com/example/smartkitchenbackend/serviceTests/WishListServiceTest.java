package com.example.smartkitchenbackend.serviceTests;

import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.entities.*;
import com.example.smartkitchenbackend.repositories.wishlist.WishListRepository;
import com.example.smartkitchenbackend.services.WishListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WishListServiceTest {

    private static final String KITCHEN_NAME = "Kitchen_Name";
    private static final long WISH_LIST_ID = 0;
    private static final double WEIGHT = 445;
    private static final String INGREDIENT_NAME = "Ingredient_Name";
    private static final String TYPE = "Type";
    private WishListService wishListService;
    @Mock
    private WishListRepository mockWishListRepository;
    @Mock
    private User mockUser;

    @Before
    public void setUp() {
        wishListService = new WishListService(mockWishListRepository);
    }

    @Test
    public void wishListIsCreated_WhenKitchenIsGiven() {
        wishListService.createWishListInKitchen(createKitchen());

        verify(mockWishListRepository).save(createWishList(createKitchen()));
    }

    @Test
    public void findByIdIsCalled_WhenIdIsGiven() {
        wishListService.findById(WISH_LIST_ID);

        verify(mockWishListRepository).findById(WISH_LIST_ID);
    }

    @Test
    public void wishedIngredientsFound_WhenWishListIdIsGiven() {
        when(mockWishListRepository.findById(WISH_LIST_ID)).thenReturn(createWishListWithIngredients());

        List<IngredientDTO> result = wishListService.findAllIngredientsByWishListId(WISH_LIST_ID);

        Assert.assertEquals(WEIGHT ,result.get(0).getWeightOrCount(), 0);
        Assert.assertEquals(TYPE, result.get(0).getType());
        Assert.assertEquals(INGREDIENT_NAME, result.get(0).getName());
    }



    private Kitchen createKitchen() {
        Kitchen kitchen = new Kitchen();
        kitchen.setName(KITCHEN_NAME + "#" + 1001);
        kitchen.setUsers(Collections.singletonList(mockUser));
        return kitchen;
    }

    private WishList createWishList(Kitchen kitchen) {
        WishList wishList = new WishList();
        wishList.setKitchen(kitchen);
        return wishList;
    }

    private WishList createWishListWithIngredients() {
        WishList wishList = new WishList();
        wishList.setKitchen(createKitchen());
        wishList.setIngredients(createWishedIngredients());
        return wishList;
    }

    private List<WishedIngredient> createWishedIngredients() {
        WishedIngredient wishedIngredient = new WishedIngredient();
        wishedIngredient.setWeightOrCount(WEIGHT);
        wishedIngredient.setIngredient(createIngredient());
        return Collections.singletonList(wishedIngredient);
    }

    private Ingredient createIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(INGREDIENT_NAME);
        ingredient.setType(TYPE);
        return ingredient;
    }
}
