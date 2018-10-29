import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {KitchenDetailDTO} from '../../model/KitchenDetailDTO';
import {KitchenService} from '../../services/kitchen.service';

@Component({
    selector: 'app-kitchen-detail',
    templateUrl: './kitchen-detail.page.html',
    styleUrls: ['./kitchen-detail.page.scss'],
})
export class KitchenDetailPage implements OnInit {
    kitchenId: number;
    kitchenDetails: KitchenDetailDTO;

    constructor(private route: ActivatedRoute, private kitchenService: KitchenService) {
    }

    ngOnInit() {
        this.kitchenId = +this.route.snapshot.paramMap.get('id');
        console.log('kitchenDetailsPage ' + this.kitchenId);
        this.getKitchenDetails();
    }

    getKitchenDetails() {
        this.kitchenService.getKitchenDetails(this.kitchenId).subscribe(data => this.kitchenDetails = data);
    }

    createKitchen() {
        this.kitchenService;
    }

}
