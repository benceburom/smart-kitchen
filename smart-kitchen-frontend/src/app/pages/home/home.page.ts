import { Component, OnInit } from '@angular/core';
import { LoginRequest } from '../../model/LoginRequest';
import { AuthenticationService } from '../../services/authentication.service';
import { NavController, Platform } from '@ionic/angular';
import { Toast } from '../../toast/toast';

@Component({
    selector: 'app-home',
    templateUrl: 'home.page.html',
    styleUrls: ['home.page.scss']
})
export class HomePage implements OnInit {
    loginRequest: LoginRequest;
    token: String;

    constructor(private authenticationService: AuthenticationService, private navCtrl: NavController, private toast: Toast) {
        this.loginRequest = new LoginRequest();
    }

    ngOnInit() {
        this.getToken();
    }

    async login() {
        try {
            await this.authenticationService.login(this.loginRequest);
            this.toast.presentToastWithOptions({
                message: 'Login success',
                showCloseButton: true,
                position: 'bottom',
                duration: 2000,
                color: 'primary',
                closeButtonText: 'Close'
            });
            const id = await this.getCurrentUserId();
            console.log(id, 'getcurrent user id res');
            this.navCtrl.navigateRoot(`/logged-in-user/${id}`);
        } catch (error) {
            console.log(error.error);
            this.toast.presentToastWithOptions({
                message: 'Invalid username or password',
                duration: 2000,
                showCloseButton: true,
                position: 'bottom',
                color: 'success',
                closeButtonText: 'Close'
            });
        }
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
