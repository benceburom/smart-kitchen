import {Component, OnInit} from '@angular/core';
import {WishListService} from '../../services/wish-list.service';
import {IngredientService} from '../../services/ingredient.service';
import {IngredientDTO} from '../../model/IngredientDTO';
import {ActivatedRoute} from '@angular/router';

@Component({
    selector: 'app-wish-list',
    templateUrl: './wish-list.page.html',
    styleUrls: ['./wish-list.page.scss'],
})
export class WishListPage implements OnInit {
    wishListId: number;
    ingredients: IngredientDTO[];
    ingredientToAdd: IngredientDTO;

    constructor(private route: ActivatedRoute, private wishListService: WishListService, private ingredientService: IngredientService) {
        this.ingredientToAdd = new IngredientDTO();
    }

    ngOnInit() {
        this.wishListId = +this.route.snapshot.paramMap.get('id');
    }

    getIngredients() {
        this.wishListService.getIngredientsByWishListId(this.wishListId).subscribe(data => this.ingredients = data);
    }

    addIngredient() {
        this.ingredientService.createInWishList(this.ingredientToAdd, this.wishListId).subscribe(() => {
        }, () => {
        }, () => {
            this.getIngredients();
        });
    }

}
