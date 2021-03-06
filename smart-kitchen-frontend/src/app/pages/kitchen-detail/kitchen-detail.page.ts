import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { KitchenDetailDTO } from '../../model/KitchenDetailDTO';
import { KitchenService } from '../../services/kitchen.service';
import { IngredientService } from '../../services/ingredient.service';
import { IngredientDTO } from '../../model/IngredientDTO';
import { FoodService } from '../../services/food.service';
import { FoodDetailDTO } from '../../model/FoodDetailDTO';
import { NavController, PopoverController, ModalController } from '@ionic/angular';
import { LogOutPopoverPage } from '../log-out-popover/log-out-popover.page';
import { AddIngredientPopoverPage } from '../add-ingredient-popover/add-ingredient-popover.page';
import { AddFoodPopoverPage } from '../add-food-popover/add-food-popover.page';
import { AddUserToKitchenModalPage } from '../add-user-to-kitchen-modal/add-user-to-kitchen-modal.page';
import { Toast } from '../../toast/toast';

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
        private foodService: FoodService,
        private ingredientService: IngredientService,
        private navCtrl: NavController,
        private toast: Toast,
        private popoverController: PopoverController,
        private modalController: ModalController) {
    }

    ngOnInit() {
        this.kitchenId = +this.route.snapshot.paramMap.get('id');
        this.getKitchenDetails();
        this.getFoodRecipes();
        this.getMakeAbleFoods();
    }

    ionViewWillEnter() {
        this.getKitchenDetails();
        this.getFoodRecipes();
        this.getMakeAbleFoods();
    }

    getKitchenDetails() {
        this.kitchenService.getKitchenDetails(this.kitchenId).subscribe(data => {
            this.kitchenDetails = data;
        });
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

    async openLogoutPopover(ev: Event) {
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
        this.getMakeAbleFoods();
    }

    async openAddFoodModal() {
        const modal = await this.modalController.create({
            component: AddFoodPopoverPage,
            componentProps: {
                custom_id: this.kitchenId
            }
        });
        await modal.present();
        await modal.onDidDismiss();
        this.getFoodRecipes();
        this.getMakeAbleFoods();
    }

    async openAddUserModal() {
        const modal = await this.modalController.create({
            component: AddUserToKitchenModalPage,
            componentProps: {
                custom_id: this.kitchenId
            }
        });
        await modal.present();
    }


    async removeIngredient(id: number) {
        await this.ingredientService.removeIngredientFromKitchen(id);
        this.toast.presentToastWithOptions({
            message: 'Ingredient removed',
            duration: 2000,
            showCloseButton: true,
            position: 'bottom',
            color: 'success',
            closeButtonText: 'Close'
        })
        this.getKitchenDetails();
    }


}
