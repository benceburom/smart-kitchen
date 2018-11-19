import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FoodDTO } from '../model/FoodDTO';
import { FoodDetailDTO } from '../model/FoodDetailDTO';
import { NewIngredientDTO } from '../model/NewIngredientDTO';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  private foodEndpontUrl = 'http://localhost:8080/api/food';

  constructor(private http: HttpClient) {
  }

  createFood(foodDTO: FoodDTO): Observable<void> {
      return this.http.post <void>(this.foodEndpontUrl + '/createFood', foodDTO);
  }

  getFoodsByKitchenId(kitchenId: number): Observable<FoodDetailDTO[]> {
    return this.http.get <FoodDetailDTO[]>(this.foodEndpontUrl + '/listFoods/' + kitchenId.toString());
  }

  getFoodDetail(id: number): Observable<FoodDetailDTO> {
    return this.http.get <FoodDetailDTO>(this.foodEndpontUrl + '/getFoodDetail/' + id.toString());
  }

  getMakeAbleFoods(kitchenId: number): Observable<FoodDetailDTO[]> {
    return this.http.get <FoodDetailDTO[]>(this.foodEndpontUrl + '/getMakeAbleFoods/' + kitchenId.toString());
  }

  getMissingIngredients(foodId: number): Observable<NewIngredientDTO[]> {
    return this.http.get <NewIngredientDTO[]>(this.foodEndpontUrl + '/getMissingIngredients/' + foodId.toString());
  }

}
