import {Component, OnInit} from '@angular/core';
import {LoginRequest} from '../../model/LoginRequest';
import {AuthenticationService} from '../../services/authentication.service';

@Component({
    selector: 'app-home',
    templateUrl: 'home.page.html',
    styleUrls: ['home.page.scss']
})
export class HomePage implements OnInit {
    loginRequest: LoginRequest;
    username = '';

    constructor(private authenticationService: AuthenticationService) {
        this.loginRequest = new LoginRequest();
    }

    ngOnInit() {
    }

    login() {
        this.authenticationService.login(this.loginRequest)
            .subscribe(result => {
                if (result) {
                    console.log('siker');
                    console.log(this.authenticationService.getToken());
                }
            }, error => {
                console.log('error');
            });
    }

    logout() {
        this.authenticationService.logout();
    }

    getMe() {
        this.authenticationService.getCurrentUserName().subscribe(uname => {
            this.username = uname;
            console.log(this.username);
        });
    }
}
