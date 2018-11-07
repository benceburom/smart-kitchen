import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { KitchenDetailDTO } from '../../model/KitchenDetailDTO';
import { KitchenService } from '../../services/kitchen.service';
import { IngredientService } from '../../services/ingredient.service';
import { IngredientDTO } from '../../model/IngredientDTO';
import { FoodService } from '../../services/food.service';
import { FoodDetailDTO } from '../../model/FoodDetailDTO';
import { Platform } from '@ionic/angular';

@Component({
    selector: 'app-kitchen-detail',
    templateUrl: './kitchen-detail.page.html',
    styleUrls: ['./kitchen-detail.page.scss'],
})
export class KitchenDetailPage implements OnInit {
    kitchenId: number;
    kitchenDetails: KitchenDetailDTO;
    ingredientToAdd: IngredientDTO;
    makeAbleFoods: FoodDetailDTO[];
    foodRecipesInKitchen: FoodDetailDTO[];

    constructor(
        private route: ActivatedRoute,
        private kitchenService: KitchenService,
        private ingredientService: IngredientService,
        private foodService: FoodService,
        private platform: Platform) {
        this.ingredientToAdd = new IngredientDTO();
    }

    ngOnInit() {
        this.kitchenId = +this.route.snapshot.paramMap.get('id');
        this.getKitchenDetails();
        this.getFoodRecipes();
        this.getMakeAbleFoods();
    }

    getKitchenDetails() {
        this.kitchenService.getKitchenDetails(this.kitchenId).subscribe(data => this.kitchenDetails = data);
    }

    createIngredientInKitchen() {
        this.ingredientService.createInKitchen(this.ingredientToAdd, this.kitchenId).subscribe(() => {
        }, () => {
        }, () => {
            this.getKitchenDetails();
        });
    }

    getMakeAbleFoods() {
        this.foodService.getMakeAbleFoods(this.kitchenId).subscribe(makeableFoods => { this.makeAbleFoods = makeableFoods; });
    }

    getFoodRecipes() {
        this.foodService.getFoodsByKitchenId(this.kitchenId).subscribe(foodRecipes => { this.foodRecipesInKitchen = foodRecipes; });
    }


}
