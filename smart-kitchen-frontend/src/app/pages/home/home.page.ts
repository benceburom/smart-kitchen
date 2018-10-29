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

    constructor(private authenticationService: AuthenticationService, private navCtrl: NavController) {
        this.loginRequest = new LoginRequest();
    }

    ngOnInit() {
    }

    login() {
        this.authenticationService.login(this.loginRequest)
            .subscribe(result => {
                if (result) {
                    this.getCurrentUserId().then(res => {
                        console.log(res);
                        this.navCtrl.navigateForward(`/logged-in-user/${res}`);
                    });
                }
            }, error => {
                console.log('error');
            });
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
}
