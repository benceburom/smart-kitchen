import { Component, OnInit } from '@angular/core';
import { LoginRequest } from '../../model/LoginRequest';
import { AuthenticationService } from '../../services/authentication.service';
import { NavController } from '@ionic/angular';

@Component({
    selector: 'app-home',
    templateUrl: 'home.page.html',
    styleUrls: ['home.page.scss']
})
export class HomePage implements OnInit {
    loginRequest: LoginRequest;
    token: String;

    constructor(private authenticationService: AuthenticationService, private navCtrl: NavController) {
        this.loginRequest = new LoginRequest();
    }

    ngOnInit() {
        this.getToken();
    }

    async login() {
        await this.authenticationService.login(this.loginRequest);
        console.log('complete');
        const id = await this.getCurrentUserId();
        console.log(id, 'getcurrent user id res');
        this.navCtrl.navigateRoot(`/logged-in-user/${id}`);
    }

    logout() {
        this.authenticationService.logout();
    }

    getCurrentUserId() {
        return new Promise(resolve => {
            this.authenticationService.getCurrentUser().subscribe(data => {
                resolve(data.id);
            });
        });

    }

    async getToken() {
        this.token = await this.authenticationService.getToken();
        if (this.token !== '') {
            const id = await this.getCurrentUserId();
            this.navCtrl.navigateRoot(`/logged-in-user/${id}`);
        }
    }
}
