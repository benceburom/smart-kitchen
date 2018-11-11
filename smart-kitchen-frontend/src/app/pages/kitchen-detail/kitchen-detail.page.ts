import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { KitchenDetailDTO } from '../../model/KitchenDetailDTO';
import { KitchenService } from '../../services/kitchen.service';
import { IngredientService } from '../../services/ingredient.service';
import { IngredientDTO } from '../../model/IngredientDTO';
import { FoodService } from '../../services/food.service';
import { FoodDetailDTO } from '../../model/FoodDetailDTO';
import { NavController, PopoverController } from '@ionic/angular';
import { LogOutPopoverPage } from '../log-out-popover/log-out-popover.page';
import { AddIngredientPopoverPage } from '../add-ingredient-popover/add-ingredient-popover.page';

@Component({
    selector: 'app-kitchen-detail',
    templateUrl: './kitchen-detail.page.html',
    styleUrls: ['./kitchen-detail.page.scss'],
})
export class KitchenDetailPage implements OnInit {
    kitchenId: number;
    kitchenDetails: KitchenDetailDTO;
    makeAbleFoods: FoodDetailDTO[];
    foodRecipesInKitchen: FoodDetailDTO[];

    constructor(
        private route: ActivatedRoute,
        private kitchenService: KitchenService,
        private ingredientService: IngredientService,
        private foodService: FoodService,
        private navCtrl: NavController,
        private popoverController: PopoverController) {
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


    getMakeAbleFoods() {
        this.foodService.getMakeAbleFoods(this.kitchenId).subscribe(makeableFoods => { this.makeAbleFoods = makeableFoods; });
    }

    getFoodRecipes() {
        this.foodService.getFoodsByKitchenId(this.kitchenId).subscribe(foodRecipes => { this.foodRecipesInKitchen = foodRecipes; });
    }

    enterWishList() {
        this.navCtrl.navigateForward(`/wish-list/${this.kitchenDetails.wishListId}`);
    }

    async openPopover(ev: Event) {
        const popover = await this.popoverController.create({
          component: LogOutPopoverPage,
          event: ev
        });
        await popover.present();
      }

      async openAddIngredientPopover(ev: Event) {
        const popover = await this.popoverController.create({
          component: AddIngredientPopoverPage,
          event: ev,
          componentProps: {
            custom_id: this.kitchenId
          }
        });
        await popover.present();
        await popover.onDidDismiss();
        this.getKitchenDetails();
      }


}
