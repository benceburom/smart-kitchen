package com.example.smartkitchenbackend.serviceTests;

import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.NewIngredientDTO;
import com.example.smartkitchenbackend.entities.*;
import com.example.smartkitchenbackend.repositories.food.FoodRepository;
import com.example.smartkitchenbackend.repositories.ingerdientInKitchen.IngredientInKitchenRepository;
import com.example.smartkitchenbackend.repositories.ingredient.IngredientRepository;
import com.example.smartkitchenbackend.repositories.neededIngredient.NeededIngredientRepository;
import com.example.smartkitchenbackend.repositories.wishedIngredient.WishedIngredientRepository;
import com.example.smartkitchenbackend.repositories.wishlist.WishListRepository;
import com.example.smartkitchenbackend.services.IngredientService;
import com.example.smartkitchenbackend.services.KitchenService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {

    private static final String INGREDIENT_NAME = "Ingredient_name";
    private static final String TYPE = "Type";
    private static final int WEIGHT_OR_COUNT = 34;
    private static final int INGREDIENT_ID = 0;
    private static final int FOOD_ID = 1;
    private static final NewIngredientDTO NEW_INGREDIENT_DTO = NewIngredientDTO.builder()
            .name(INGREDIENT_NAME)
            .type(TYPE)
            .weightOrCount(WEIGHT_OR_COUNT)
            .build();
    private static final int KITCHEN_ID = 1;
    private static final int WISHLIST_ID = 1;

    private IngredientService ingredientService;
    @Mock
    private WishListRepository mockWishListRepository;
    @Mock
    private KitchenService mockKitchenService;
    @Mock
    private FoodRepository mockFoodRepository;
    @Mock
    private IngredientRepository mockIngredientRepository;
    @Mock
    private IngredientInKitchenRepository mockIngredientInKitchenRepository;
    @Mock
    private NeededIngredientRepository mockNeededIngredientRepository;
    @Mock
    private WishedIngredientRepository mockWishedIngredientRepository;
    @Mock
    private Food mockFood;
    @Mock
    private Kitchen mockKitchen;
    @Mock
    private WishList mockWishList;

    @Before
    public void setUp() {
        ingredientService = new IngredientService(mockWishListRepository,
                mockKitchenService,
                mockFoodRepository,
                mockIngredientRepository,
                mockIngredientInKitchenRepository,
                mockNeededIngredientRepository,
                mockWishedIngredientRepository);
    }

    @Test
    public void returnsCorrectNeededIngredient_WhenIngredientAlreadyExists() {
        when(mockIngredientRepository.existsByName(INGREDIENT_NAME)).thenReturn(true);
        when(mockIngredientRepository.findByName(INGREDIENT_NAME)).thenReturn(createIngredient());
        when(mockNeededIngredientRepository.existsByIngredientIdAndFoodId(INGREDIENT_ID, FOOD_ID)).thenReturn(true);
        when(mockNeededIngredientRepository.findByIngredientIdAndFoodId(INGREDIENT_ID, FOOD_ID)).thenReturn(createNeededIngredient());
        when(mockFoodRepository.findReference(FOOD_ID)).thenReturn(mockFood);

        NeededIngredient result = ingredientService.createInFood(NEW_INGREDIENT_DTO, FOOD_ID);
        NeededIngredient neededIngredient = createNeededIngredient();
        neededIngredient.setWeightOrCount(neededIngredient.getWeightOrCount() + WEIGHT_OR_COUNT);

        verify(mockNeededIngredientRepository).save(neededIngredient);
        Assert.assertEquals(createNeededIngredient().getWeightOrCount() + WEIGHT_OR_COUNT, result.getWeightOrCount(), 0);
        Assert.assertEquals(mockFood, result.getFood());
        Assert.assertEquals(createIngredient(), result.getIngredient());
    }

    @Test
    public void returnsCorrectNeededIngredient_WhenIngredientNotExists() {
        when(mockIngredientRepository.existsByName(INGREDIENT_NAME)).thenReturn(false);
        when(mockNeededIngredientRepository.existsByIngredientIdAndFoodId(INGREDIENT_ID, FOOD_ID)).thenReturn(false);
        when(mockFoodRepository.findReference(FOOD_ID)).thenReturn(mockFood);

        NeededIngredient result = ingredientService.createInFood(NEW_INGREDIENT_DTO, FOOD_ID);

        verify(mockIngredientRepository).save(createIngredient());
        Assert.assertEquals(WEIGHT_OR_COUNT, result.getWeightOrCount(), 0);
        Assert.assertEquals(mockFood, result.getFood());
        Assert.assertEquals(createIngredient(), result.getIngredient());
    }

    @Test
    public void returnsCorrectIngredientInKitchen_WhenIngredientAlreadyExists() {
        when(mockIngredientRepository.existsByName(INGREDIENT_NAME)).thenReturn(true);
        when(mockIngredientRepository.findByName(INGREDIENT_NAME)).thenReturn(createIngredient());
        when(mockIngredientInKitchenRepository.existsByIngredientIdAndKitchenId(INGREDIENT_ID, KITCHEN_ID)).thenReturn(true);
        when(mockIngredientInKitchenRepository.findByIngredientIdAndKitchenId(INGREDIENT_ID, KITCHEN_ID)).thenReturn(createIngredientInKitchen());

        IngredientDTO result = ingredientService.createInKitchen(NEW_INGREDIENT_DTO, KITCHEN_ID);
        IngredientInKitchen ingredientInKitchen = createIngredientInKitchen();
        ingredientInKitchen.setWeightOrCount(ingredientInKitchen.getWeightOrCount() + WEIGHT_OR_COUNT);

        verify(mockIngredientInKitchenRepository).save(ingredientInKitchen);
        Assert.assertEquals(createIngredientInKitchen().getWeightOrCount()+ WEIGHT_OR_COUNT, result.getWeightOrCount() , 0);
        Assert.assertEquals(INGREDIENT_NAME, result.getName());
        Assert.assertEquals(TYPE, result.getType());
    }

    @Test
    public void returnsCorrectIngredientInKitchen_WhenIngredientNotExists() {
        when(mockIngredientRepository.existsByName(INGREDIENT_NAME)).thenReturn(false);
        when(mockIngredientInKitchenRepository.existsByIngredientIdAndKitchenId(INGREDIENT_ID, KITCHEN_ID)).thenReturn(false);
        when(mockKitchenService.findById(KITCHEN_ID)).thenReturn(mockKitchen);

        IngredientDTO result = ingredientService.createInKitchen(NEW_INGREDIENT_DTO, KITCHEN_ID);

        verify(mockIngredientRepository).save(createIngredient());
        Assert.assertEquals(WEIGHT_OR_COUNT, result.getWeightOrCount() , 0);
        Assert.assertEquals(INGREDIENT_NAME, result.getName());
        Assert.assertEquals(TYPE, result.getType());
    }

    @Test
    public void returnsCorrectWishedIngredient_WhenIngredientAlreadyExists() {
        when(mockIngredientRepository.existsByName(INGREDIENT_NAME)).thenReturn(true);
        when(mockIngredientRepository.findByName(INGREDIENT_NAME)).thenReturn(createIngredient());
        when(mockWishedIngredientRepository.existsByIngredientIdAndWishListId(INGREDIENT_ID, WISHLIST_ID)).thenReturn(true);
        when(mockWishedIngredientRepository.findByIngredientIdAndWishListId(INGREDIENT_ID, WISHLIST_ID)).thenReturn(createWishedIngredient());

        IngredientDTO result = ingredientService.createInWishList(NEW_INGREDIENT_DTO, WISHLIST_ID);
        WishedIngredient wishedIngredient = createWishedIngredient();
        wishedIngredient.setWeightOrCount(wishedIngredient.getWeightOrCount() + WEIGHT_OR_COUNT);

        verify(mockWishedIngredientRepository).save(wishedIngredient);
        Assert.assertEquals(WEIGHT_OR_COUNT, result.getWeightOrCount() , 0);
        Assert.assertEquals(INGREDIENT_NAME, result.getName());
        Assert.assertEquals(TYPE, result.getType());
    }

    @Test
    public void returnsCorrectWishedIngredient_WhenIngredientNotExists() {
        when(mockIngredientRepository.existsByName(INGREDIENT_NAME)).thenReturn(false);
        when(mockWishedIngredientRepository.existsByIngredientIdAndWishListId(INGREDIENT_ID, WISHLIST_ID)).thenReturn(false);
        when(mockWishListRepository.findReference(WISHLIST_ID)).thenReturn(mockWishList);

        IngredientDTO result = ingredientService.createInWishList(NEW_INGREDIENT_DTO, WISHLIST_ID);

        verify(mockIngredientRepository).save(createIngredient());
        Assert.assertEquals(WEIGHT_OR_COUNT, result.getWeightOrCount() , 0);
        Assert.assertEquals(INGREDIENT_NAME, result.getName());
        Assert.assertEquals(TYPE, result.getType());
    }

    @Test
    public void deleteCalled_WhenWishedIngredientIdIsGiven() {
        ingredientService.removeFromWishList(INGREDIENT_ID);

        verify(mockWishedIngredientRepository).deleteById(INGREDIENT_ID);
    }

    private IngredientInKitchen createIngredientInKitchen() {
        IngredientInKitchen ingredientInKitchen = new IngredientInKitchen();
        ingredientInKitchen.setWeightOrCount(WEIGHT_OR_COUNT);
        ingredientInKitchen.setKitchen(mockKitchen);
        return ingredientInKitchen;
    }

    private Ingredient createIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setName(INGREDIENT_NAME);
        ingredient.setType(TYPE);
        return ingredient;
    }

    private NeededIngredient createNeededIngredient() {
        NeededIngredient neededIngredient = new NeededIngredient();
        neededIngredient.setWeightOrCount(WEIGHT_OR_COUNT);
        neededIngredient.setFood(mockFood);
        return neededIngredient;
    }

    private WishedIngredient createWishedIngredient() {
        WishedIngredient wishedIngredient = new WishedIngredient();
        wishedIngredient.setWeightOrCount(WEIGHT_OR_COUNT);
        wishedIngredient.setWishList(mockWishList);
        return wishedIngredient;
    }

}
