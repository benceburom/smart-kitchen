import { Component, OnInit } from '@angular/core';
import { IngredientDTO } from '../../model/IngredientDTO';
import { IngredientService } from '../../services/ingredient.service';
import { NavParams, PopoverController } from '@ionic/angular';
import { KitchenDetailPage } from '../kitchen-detail/kitchen-detail.page';
import { Toast } from '../../toast/toast';

@Component({
  selector: 'app-add-ingredient-popover',
  templateUrl: './add-ingredient-popover.page.html',
  styleUrls: ['./add-ingredient-popover.page.scss'],
})
export class AddIngredientPopoverPage implements OnInit {
  kitchenId: number;
  ingredientToAdd: IngredientDTO;

  constructor(private ingredientService: IngredientService,
    private navParams: NavParams,
    private popoverController: PopoverController,
    private toast: Toast) {
  }

  ngOnInit() {
    this.ingredientToAdd = new IngredientDTO();
    this.kitchenId = this.navParams.get('custom_id');
  }

  createIngredientInKitchen() {
    this.ingredientService.createInKitchen(this.ingredientToAdd, this.kitchenId).subscribe(() => {
    }, () => {
    }, () => {
      this.ingredientToAdd.name = null;
      this.ingredientToAdd.weightOrCount = null;
      this.toast.presentToastWithOptions({
        message: 'Ingredient added',
        duration: 2000,
        showCloseButton: true,
        position: 'bottom',
        color: 'success',
        closeButtonText: 'Close'
      });
      this.popoverController.dismiss();
    });
  }

}
