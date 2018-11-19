package com.example.smartkitchenbackend.serviceTests;

import com.example.smartkitchenbackend.DTOs.Food.FoodDTO;
import com.example.smartkitchenbackend.DTOs.Food.FoodDetailDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.NewIngredientDTO;
import com.example.smartkitchenbackend.entities.*;
import com.example.smartkitchenbackend.repositories.food.FoodRepository;
import com.example.smartkitchenbackend.services.FoodService;
import com.example.smartkitchenbackend.services.IngredientService;
import com.example.smartkitchenbackend.services.KitchenService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FoodServiceTest {

    private static final String KITCHEN_NAME = "KitchenName";
    private static final String FOOD_NAME = "FoodName";
    private static final String INGREDIENT_NAME = "IngredientName";
    private static final String INGREDIENT_TYPE = "type";
    private static final int WEIGHT_OR_COUNT = 35;
    private static final int KITCHEN_ID = 1;
    private static final long FOOD_ID = 0;

    private static final NewIngredientDTO NEW_INGREDIENT_DTO = NewIngredientDTO.builder()
            .name(INGREDIENT_NAME)
            .type(INGREDIENT_TYPE)
            .weightOrCount(WEIGHT_OR_COUNT)
            .build();
    private static final FoodDTO FOOD_DTO = FoodDTO.builder()
            .kitchenId(KITCHEN_ID)
            .name(FOOD_NAME)
            .ingredients(Collections.singletonList(NEW_INGREDIENT_DTO))
            .build();

    private FoodService foodService;
    @Mock
    private FoodRepository mockFoodRepository;
    @Mock
    private KitchenService mockKitchenService;
    @Mock
    private IngredientService mockIngredientService;
    @Mock
    private Food mockFood;

    @Before
    public void setUp() {
        foodService = new FoodService(mockFoodRepository, mockKitchenService, mockIngredientService);
    }

    @Test
    public void savesFoodWithTheGivenInputs() {
        when(mockKitchenService.findById(KITCHEN_ID)).thenReturn(dummyKitchen());
        when(mockIngredientService.createInFood(NEW_INGREDIENT_DTO, 0)).thenReturn(dummyNeededIngredient());

        foodService.create(FOOD_DTO);

        verify(mockFoodRepository, times(2)).save(createFood());
    }

    @Test
    public void allFoodInKitchenIsFound_WhenKitchenIdIsGiven() {
        when(mockKitchenService.findById(KITCHEN_ID)).thenReturn(dummyKitchen());

        List<FoodDetailDTO> result = foodService.getFoodsByKitchenId(KITCHEN_ID);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(FOOD_NAME, result.get(0).getName());
        Assert.assertEquals(INGREDIENT_NAME, result.get(0).getIngredients().get(0).getName());
        Assert.assertEquals(INGREDIENT_TYPE, result.get(0).getIngredients().get(0).getType());
    }

    @Test
    public void foodDetailDtoReturned_WhenFoodIdIsGiven() {
        when(mockFoodRepository.findById(FOOD_ID)).thenReturn(createFood());

        FoodDetailDTO result = foodService.getFoodDetails(FOOD_ID);

        Assert.assertEquals(FOOD_NAME, result.getName());
        Assert.assertEquals(NEW_INGREDIENT_DTO.getWeightOrCount(), result.getIngredients().get(0).getWeightOrCount(),0);
    }

    @Test
    public void makeAbleFoodsFoundInKitchen_WhenKitchenIdIsGiven() {
        when(mockKitchenService.findById(KITCHEN_ID)).thenReturn(dummyKitchen());

        List<FoodDetailDTO> result = foodService.getMakeableFoodsInKitchen(KITCHEN_ID);

        Assert.assertEquals(FOOD_NAME, result.get(0).getName());
    }

    @Test
    public void noMissingIngredientsFoundForFoodWithGivenFoodId_WhenTheyAreNotMissing() {
        when(mockFoodRepository.findById(FOOD_ID)).thenReturn(createFood());

        List<NewIngredientDTO> result = foodService.getMissingIngredientsForFood(FOOD_ID);

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void missingIngredientsFoundForFoodWithGivenFoodId_WhenTheyAreMissing() {
        when(mockFoodRepository.findById(FOOD_ID)).thenReturn(createFoodWithMissingIngredients());

        List<NewIngredientDTO> result = foodService.getMissingIngredientsForFood(FOOD_ID);

        Assert.assertEquals(1, result.size());
    }


    private Kitchen dummyKitchen() {
        Kitchen kitchen = new Kitchen();
        kitchen.setName(KITCHEN_NAME);
        kitchen.setFoods(Collections.singletonList(foodWithoutKitchen()));
        kitchen.setIngredients(Collections.singletonList(dummyIngredientInKitchen()));
        return kitchen;
    }

    private NeededIngredient dummyNeededIngredient() {
        NeededIngredient neededIngredient = new NeededIngredient();
        neededIngredient.setWeightOrCount(NEW_INGREDIENT_DTO.getWeightOrCount());
        neededIngredient.setFood(mockFood);
        neededIngredient.setIngredient(dummyIngredient());
        return neededIngredient;
    }


    private IngredientInKitchen dummyIngredientInKitchen() {
        IngredientInKitchen ingredientInKitchen = new IngredientInKitchen();
        ingredientInKitchen.setWeightOrCount(NEW_INGREDIENT_DTO.getWeightOrCount());
        ingredientInKitchen.setIngredient(dummyIngredient());
        return ingredientInKitchen;
    }

    private Food createFood() {
        Food food = new Food();
        food.setName(FOOD_NAME);
        food.setKitchen(dummyKitchen());
        food.setIngredients(Collections.singletonList(dummyNeededIngredient()));
        return food;
    }

    private Food createFoodWithMissingIngredients() {
        Kitchen kitchen = dummyKitchen();
        kitchen.setIngredients(new ArrayList<>());
        Food food = new Food();
        food.setName(FOOD_NAME);
        food.setKitchen(kitchen);
        food.setIngredients(Collections.singletonList(dummyNeededIngredient()));
        return food;
    }

    private Food foodWithoutKitchen() {
        Food food = new Food();
        food.setName(FOOD_NAME);
        food.setIngredients(Collections.singletonList(dummyNeededIngredient()));
        return food;
    }

    private Ingredient dummyIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(INGREDIENT_NAME);
        ingredient.setType(INGREDIENT_TYPE);
        return ingredient;
    }


}
