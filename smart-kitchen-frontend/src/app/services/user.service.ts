import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {KitchenDTO} from '../model/KitchenDTO';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private userEndpointUrl = 'http://192.168.0.24:8080/api/user';

    constructor(private http: HttpClient) {
    }

    getKitchensByUserId(userId: number): Observable<KitchenDTO[]> {
        return this.http.get <KitchenDTO[]>(this.userEndpointUrl + '/listKitchens/' + userId.toString());
    }
}
