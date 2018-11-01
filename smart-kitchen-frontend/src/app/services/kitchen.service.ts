import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {KitchenDetailDTO} from '../model/KitchenDetailDTO';
import {NewKitchenDTO} from '../model/NewKitchenDTO';

@Injectable({
    providedIn: 'root'
})
export class KitchenService {
    private kitchenEndpointUrl = 'http://192.168.0.24:8080/api/kitchen';

    constructor(private http: HttpClient) {
    }

    getKitchenDetails(kitchenId: number): Observable<KitchenDetailDTO> {
        return this.http.get <KitchenDetailDTO>(this.kitchenEndpointUrl + '/kitchenDetails/' + kitchenId.toString());
    }

    createKitchen(newKitchenDTO: NewKitchenDTO): Observable<NewKitchenDTO> {
        return this.http.post <NewKitchenDTO>(this.kitchenEndpointUrl + '/create/', newKitchenDTO);
    }
}
