import { Component, OnInit } from '@angular/core';
import { FoodDTO } from '../../model/FoodDTO';
import { NavParams, ModalController } from '@ionic/angular';
import { FoodService } from '../../services/food.service';
import { NewIngredientDTO } from '../../model/NewIngredientDTO';
import { Toast } from '../../toast/toast';

@Component({
  selector: 'app-add-food-popover',
  templateUrl: './add-food-popover.page.html',
  styleUrls: ['./add-food-popover.page.scss'],
})
export class AddFoodPopoverPage implements OnInit {
  kitchenId: number;
  foodToAdd: FoodDTO;
  ingredientInFood: NewIngredientDTO;
  ingredients = [];

  constructor(private navParams: NavParams,
    private modalController: ModalController,
    private foodService: FoodService,
    private toast: Toast) { }

  ngOnInit() {
    this.foodToAdd = new FoodDTO();
    this.kitchenId = this.navParams.get('custom_id');
    this.ingredientInFood = new NewIngredientDTO;
  }

  createFoodInKitchen() {
    this.foodToAdd.kitchenId = this.kitchenId;
    this.foodToAdd.ingredients = this.ingredients;
    this.foodService.createFood(this.foodToAdd).subscribe(() => {
    }, () => {
    }, () => {
      this.toast.presentToastWithOptions({
        message: 'Food recipe saved',
        duration: 2000,
        showCloseButton: true,
        position: 'bottom',
        color: 'success',
        closeButtonText: 'Close'
      });
      this.modalController.dismiss();
    });
  }

  async createIngredientInFood() {
    console.log(this.ingredientInFood);
    const ingredientToPush = new NewIngredientDTO();
    ingredientToPush.name = this.ingredientInFood.name;
    ingredientToPush.weightOrCount = this.ingredientInFood.weightOrCount;
    await this.ingredients.push(ingredientToPush);
    this.toast.presentToastWithOptions({
      message: 'Ingredient added to recipe',
      duration: 2000,
      showCloseButton: true,
      position: 'bottom',
      color: 'success',
      closeButtonText: 'Close'
    });
    this.ingredientInFood.name = null;
    this.ingredientInFood.weightOrCount = null;
  }

  closeModal() {
    this.modalController.dismiss();
  }


}
