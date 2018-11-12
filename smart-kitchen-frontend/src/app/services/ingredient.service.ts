import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IngredientDTO} from '../model/IngredientDTO';
import {Observable} from 'rxjs';
import { NewIngredientDTO } from '../model/NewIngredientDTO';

@Injectable({
    providedIn: 'root'
})
export class IngredientService {
    private ingredientEndpointUrl = 'http://192.168.0.24:8080/api/ingredient';

    constructor(private http: HttpClient) {
    }

    createInKitchen(newIngredientDTO: NewIngredientDTO, kitchenId: number): Observable<IngredientDTO> {
        return this.http.post <IngredientDTO>(this.ingredientEndpointUrl + '/createInKitchen/' + kitchenId.toString(), newIngredientDTO);
    }

    createInWishList(newIngredientDTO: NewIngredientDTO, wishListId: number): Observable<IngredientDTO> {
        return this.http.post <IngredientDTO>(this.ingredientEndpointUrl + '/createInWishList/' + wishListId.toString(), newIngredientDTO);
    }

    createMultipleInWishList(ingredientsToAdd: NewIngredientDTO[], wishListId): Observable<void> {
        return this.http.post <void>(this.ingredientEndpointUrl + '/createMultipleInWishList/' + wishListId.toString(), ingredientsToAdd);
    }

    removeIngredient(wishedIngredientId: number): Observable<void> {
        return this.http.delete <void>(this.ingredientEndpointUrl + '/removeFromWishList/' + wishedIngredientId.toString());
    }
}
