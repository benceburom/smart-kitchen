import {Component, OnInit} from '@angular/core';
import {LoginRequest} from '../../model/LoginRequest';
import {AuthenticationService} from '../../services/authentication.service';
import {NavController} from '@ionic/angular';

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
    }

    async login() {
        await this.authenticationService.login(this.loginRequest);
        console.log('complete');
        const id = await this.getCurrentUserId();
        console.log(id, 'getcurrent user id res');
        this.navCtrl.navigateForward(`/logged-in-user/${id}`);
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

    getToken() {
        this.authenticationService.getToken().then(result => {
            this.token = result.toString();
        });
    }
}
