import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {KitchenDetailDTO} from '../model/KitchenDetailDTO';
import {NewKitchenDTO} from '../model/NewKitchenDTO';

@Injectable({
    providedIn: 'root'
})
export class KitchenService {
    private kitchenEndpointUrl = 'http://localhost:8080/api/kitchen';

    constructor(private http: HttpClient) {
    }

    getKitchenDetails(kitchenId: number): Observable<KitchenDetailDTO> {
        return this.http.get <KitchenDetailDTO>(this.kitchenEndpointUrl + '/kitchenDetails/' + kitchenId.toString());
    }

    getUserIdsInKitchen(kitchenId: number): Promise<number[]> {
        return this.http.get<KitchenDetailDTO>(this.kitchenEndpointUrl + '/kitchenDetails/' + kitchenId.toString())
            .toPromise().then(data => data.userIds);
    }

    createKitchen(newKitchenDTO: NewKitchenDTO): Observable<void> {
        return this.http.post <void>(this.kitchenEndpointUrl + '/create/', newKitchenDTO);
    }
}
