import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../../services/user.service';
import {KitchenDTO} from '../../model/KitchenDTO';
import {KitchenService} from '../../services/kitchen.service';
import {NewKitchenDTO} from '../../model/NewKitchenDTO';

@Component({
    selector: 'app-logged-in-user',
    templateUrl: './logged-in-user.page.html',
    styleUrls: ['./logged-in-user.page.scss'],
})
export class LoggedInUserPage implements OnInit {
    userId: number;
    kitchens: KitchenDTO[];
    newKitchen: NewKitchenDTO;

    constructor(private userService: UserService, private kitchenService: KitchenService, private route: ActivatedRoute) {
        this.newKitchen = new NewKitchenDTO();
    }

    ngOnInit() {
        this.userId = +this.route.snapshot.paramMap.get('id');
        this.getKitchensByUserId();
        console.log('im here' + this.userId);
    }

    getKitchensByUserId() {
        this.userService.getKitchensByUserId(this.userId).subscribe(data => {
            this.kitchens = data;
        });
    }

    createKitchen() {
        this.newKitchen.userId = this.userId;
        this.kitchenService.createKitchen(this.newKitchen).subscribe(() => {
        }, () => {
        }, () => {
            this.getKitchensByUserId();
        });
    }

}
