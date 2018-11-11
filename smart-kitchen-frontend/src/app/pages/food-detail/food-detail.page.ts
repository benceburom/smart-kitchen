import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FoodService } from '../../services/food.service';
import { FoodDetailDTO } from '../../model/FoodDetailDTO';
import { NewIngredientDTO } from '../../model/NewIngredientDTO';

@Component({
  selector: 'app-food-detail',
  templateUrl: './food-detail.page.html',
  styleUrls: ['./food-detail.page.scss'],
})
export class FoodDetailPage implements OnInit {
  foodId: number;
  foodDetail: FoodDetailDTO;
  missingIngredients: NewIngredientDTO[];

  constructor(private route: ActivatedRoute, private foodService: FoodService) { }

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

}
