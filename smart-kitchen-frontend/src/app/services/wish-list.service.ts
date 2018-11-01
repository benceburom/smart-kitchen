import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IngredientDTO} from '../model/IngredientDTO';

@Injectable({
    providedIn: 'root'
})
export class WishListService {

    private wishListControllerUrl = 'http://192.168.0.24:8080/api/wishList';

    constructor(private http: HttpClient) {
    }

    getIngredientsByWishListId(id: number): Observable<IngredientDTO[]> {
        return this.http.get <IngredientDTO[]>(this.wishListControllerUrl + '/listIngredients/' + id.toString());
    }
}
