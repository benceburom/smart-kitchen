import { Component, OnInit } from '@angular/core';
import { WishListService } from '../../services/wish-list.service';
import { IngredientService } from '../../services/ingredient.service';
import { IngredientDTO } from '../../model/IngredientDTO';
import { ActivatedRoute } from '@angular/router';
import { LogOutPopoverPage } from '../log-out-popover/log-out-popover.page';
import { PopoverController } from '@ionic/angular';
import { Toast } from '../../toast/toast';

@Component({
    selector: 'app-wish-list',
    templateUrl: './wish-list.page.html',
    styleUrls: ['./wish-list.page.scss'],
})
export class WishListPage implements OnInit {
    wishListId: number;
    ingredients: IngredientDTO[];
    ingredientToAdd: IngredientDTO;

    constructor(private route: ActivatedRoute,
        private wishListService: WishListService,
        private ingredientService: IngredientService,
        private popoverController: PopoverController,
        private toast: Toast) {
        this.ingredientToAdd = new IngredientDTO();
    }

    ngOnInit() {
        this.wishListId = +this.route.snapshot.paramMap.get('id');
        this.getIngredients();
    }

    getIngredients() {
        this.wishListService.getIngredientsByWishListId(this.wishListId).subscribe(data => this.ingredients = data);
    }

    addIngredient() {
        this.ingredientService.createInWishList(this.ingredientToAdd, this.wishListId).subscribe(() => {
        }, () => {
        }, () => {
            this.toast.presentToastWithOptions({
                message: 'Ingredient added to wishlist',
                duration: 2000,
                showCloseButton: true,
                position: 'bottom',
                color: 'success',
                closeButtonText: 'Close'
              });
            this.getIngredients();
        });
    }

    async openLogoutPopover(ev: Event) {
        const popover = await this.popoverController.create({
            component: LogOutPopoverPage,
            event: ev
        });
        await popover.present();
    }

}
