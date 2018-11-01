import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {KitchenDetailDTO} from '../../model/KitchenDetailDTO';
import {KitchenService} from '../../services/kitchen.service';
import {IngredientService} from '../../services/ingredient.service';
import {IngredientDTO} from '../../model/IngredientDTO';

@Component({
    selector: 'app-kitchen-detail',
    templateUrl: './kitchen-detail.page.html',
    styleUrls: ['./kitchen-detail.page.scss'],
})
export class KitchenDetailPage implements OnInit {
    kitchenId: number;
    kitchenDetails: KitchenDetailDTO;
    ingredientToAdd: IngredientDTO;

    constructor(private route: ActivatedRoute, private kitchenService: KitchenService, private ingredientService: IngredientService) {
        this.ingredientToAdd = new IngredientDTO();
    }

    ngOnInit() {
        this.kitchenId = +this.route.snapshot.paramMap.get('id');
        console.log('kitchenDetailsPage ' + this.kitchenId);
        this.getKitchenDetails();
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

}
