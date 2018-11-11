import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../../services/user.service';
import {KitchenDTO} from '../../model/KitchenDTO';
import {KitchenService} from '../../services/kitchen.service';
import {NewKitchenDTO} from '../../model/NewKitchenDTO';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
    selector: 'app-logged-in-user',
    templateUrl: './logged-in-user.page.html',
    styleUrls: ['./logged-in-user.page.scss'],
})
export class LoggedInUserPage implements OnInit {
    userId: number;
    kitchens: KitchenDTO[];
    newKitchen: NewKitchenDTO;
    nameOfUser: String;

    constructor(private userService: UserService,
        private kitchenService: KitchenService,
        private route: ActivatedRoute,
        private authService: AuthenticationService) {
        this.newKitchen = new NewKitchenDTO();
    }

    ngOnInit() {
        this.userId = +this.route.snapshot.paramMap.get('id');
        this.getKitchensByUserId();
        this.getUserName();
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
            this.newKitchen.name = null;
            this.getKitchensByUserId();
        });
    }

    getUserName() {
        this.authService.getCurrentUser().subscribe(currentUser => this.nameOfUser = currentUser.name );
    }

}
