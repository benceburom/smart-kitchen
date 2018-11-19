package com.example.smartkitchenbackend.serviceTests;

import com.example.smartkitchenbackend.DTOs.Kitchen.NewKitchenDTO;
import com.example.smartkitchenbackend.entities.Kitchen;
import com.example.smartkitchenbackend.entities.User;
import com.example.smartkitchenbackend.entities.WishList;
import com.example.smartkitchenbackend.repositories.kitchen.KitchenRepository;
import com.example.smartkitchenbackend.repositories.user.UserRepository;
import com.example.smartkitchenbackend.services.KitchenService;
import com.example.smartkitchenbackend.services.WishListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KitchenServiceTest {

    private static final String KITCHEN_NAME = "KitchenName";
    private static final long USER_ID = 0;
    private static final long KITCHEN_ID = 0;
    private static final NewKitchenDTO NEW_KITCHEN_DTO = NewKitchenDTO.builder()
            .name(KITCHEN_NAME)
            .userId(USER_ID)
            .build();

    private KitchenService kitchenService;
    @Mock
    private KitchenRepository mockKitchenRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private WishListService mockWishListService;
    @Mock
    private User mockUser;
    @Mock
    private WishList mockWishList;

    @Before
    public void setUp() {
        kitchenService = new KitchenService(mockKitchenRepository, mockUserRepository, mockWishListService);
    }

    @Test
    public void kitchenIsCreated_WhenKitchenDTOIsGiven() {
        when(mockKitchenRepository.numberOfKitchensWithSimilarNames(KITCHEN_NAME)).thenReturn(0);
        when(mockUserRepository.getOne(USER_ID)).thenReturn(mockUser);
        when(mockWishListService.createWishListInKitchen(createKitchen())).thenReturn(mockWishList);

        kitchenService.create(NEW_KITCHEN_DTO);
        Kitchen kitchen = createKitchen();
        kitchen.setWishList(mockWishList);

        verify(mockKitchenRepository, times(2)).save(kitchen);
    }

    @Test
    public void findByIdIsCalled_WhenKitchenIdIsGiven() {
        kitchenService.findById(KITCHEN_ID);

        verify(mockKitchenRepository).findById(KITCHEN_ID);
    }

    private Kitchen createKitchen() {
        Kitchen kitchen = new Kitchen();
        kitchen.setName(KITCHEN_NAME + "#" + 1001);
        kitchen.setUsers(Collections.singletonList(mockUser));
        return kitchen;
    }


}
