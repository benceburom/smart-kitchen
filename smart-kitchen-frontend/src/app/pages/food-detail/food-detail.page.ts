import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FoodService } from '../../services/food.service';
import { FoodDetailDTO } from '../../model/FoodDetailDTO';
import { NewIngredientDTO } from '../../model/NewIngredientDTO';
import { LogOutPopoverPage } from '../log-out-popover/log-out-popover.page';
import { PopoverController } from '@ionic/angular';
import { IngredientService } from '../../services/ingredient.service';
import { Toast } from '../../toast/toast';

@Component({
  selector: 'app-food-detail',
  templateUrl: './food-detail.page.html',
  styleUrls: ['./food-detail.page.scss'],
})
export class FoodDetailPage implements OnInit {
  foodId: number;
  foodDetail: FoodDetailDTO;
  missingIngredients: NewIngredientDTO[];

  constructor(private route: ActivatedRoute,
    private foodService: FoodService,
    private popoverController: PopoverController,
    private ingredientService: IngredientService,
    private toast: Toast) { }

  ngOnInit() {
    this.foodId = +this.route.snapshot.paramMap.get('id');
    this.getFoodDetails();
    this.getMissingIngredients();
  }

  getFoodDetails() {
    this.foodService.getFoodDetail(this.foodId).subscribe(data => this.foodDetail = data);
  }

  getMissingIngredients() {
    this.foodService.getMissingIngredients(this.foodId).subscribe(data => this.missingIngredients = data);
  }

  async openLogoutPopover(ev: Event) {
    const popover = await this.popoverController.create({
      component: LogOutPopoverPage,
      event: ev
    });
    await popover.present();
  }

  addMissingIngredientsToWishList() {
    this.ingredientService.createMultipleInWishList(this.missingIngredients, this.foodId).subscribe(() => {
      this.toast.presentToastWithOptions({
        message: 'Ingredients added to wishlist',
        duration: 2000,
        showCloseButton: true,
        position: 'bottom',
        color: 'success',
        closeButtonText: 'Close'
      });
    });
  }

}
