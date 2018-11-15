package com.example.smartkitchenbackend.endpoints;

import com.example.smartkitchenbackend.DTOs.ingredient.IngredientDTO;
import com.example.smartkitchenbackend.DTOs.ingredient.NewIngredientDTO;
import com.example.smartkitchenbackend.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
@RequiredArgsConstructor
public class IngredientEndpoint {
    private final IngredientService ingredientService;

    @PostMapping("/createInKitchen/{kitchenId}")
    public IngredientDTO createIngredientInKitchen(@RequestBody NewIngredientDTO newIngredientDTO, @PathVariable long kitchenId) {
        return ingredientService.createInKitchen(newIngredientDTO, kitchenId);
    }

    @PostMapping("/createInWishList/{wishListId}")
    public IngredientDTO createIngredientInWishList(@RequestBody NewIngredientDTO newIngredientDTO, @PathVariable long wishListId) {
        return ingredientService.createInWishList(newIngredientDTO, wishListId);
    }

    @DeleteMapping("/removeFromWishList/{wishedIngredientId}")
    public void removeFromWishList(@PathVariable long wishedIngredientId) {
        ingredientService.removeFromWishList(wishedIngredientId);
    }

    @PostMapping("createMultipleInWishListFromFood/{foodId}")
    public void createMultipleInWishListFromFood(@RequestBody List<NewIngredientDTO> newIngredientDTO, @PathVariable long foodId) {
        ingredientService.createMultipleInWishList(newIngredientDTO, foodId);
    }

    @PostMapping("createMultipleInKitchenFromWishList/{wishListId}")
    public void createMultipleInKitchen(@RequestBody List<NewIngredientDTO> newIngredientDTO, @PathVariable long wishListId) {
        ingredientService.createMultipleInKitchen(newIngredientDTO, wishListId);
    }
}
