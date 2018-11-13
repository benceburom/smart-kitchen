import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {KitchenDTO} from '../model/KitchenDTO';
import { UserDTO } from '../model/UserDTO';
import { AddKitchenDTO } from '../model/AddKitchenDTO';

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

    listUsers(): Observable<UserDTO[]> {
        return this.http.get <UserDTO[]>(this.userEndpointUrl + '/list');
    }

    addUserToKitchen(addKitchenDTO: AddKitchenDTO): Observable<KitchenDTO> {
        return this.http.post <KitchenDTO>(this.userEndpointUrl + '/addKitchen' , addKitchenDTO);
    }
}
