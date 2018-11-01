import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IngredientDTO} from '../model/IngredientDTO';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class IngredientService {
    private ingredientEndpointUrl = 'http://192.168.0.24:8080/api/ingredient';

    constructor(private http: HttpClient) {
    }

    createInKitchen(ingredientDTO: IngredientDTO, kitchenId: number): Observable<IngredientDTO> {
        return this.http.post <IngredientDTO>(this.ingredientEndpointUrl + '/createInKitchen/' + kitchenId.toString(), ingredientDTO);
    }

    createInWishList(ingredientDTO: IngredientDTO, wishListId: number): Observable<IngredientDTO> {
        return this.http.post <IngredientDTO>(this.ingredientEndpointUrl + '/createInWishList/' + wishListId.toString(), ingredientDTO);
    }
}
