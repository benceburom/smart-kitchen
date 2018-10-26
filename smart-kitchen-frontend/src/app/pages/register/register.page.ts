import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {SignUpRequest} from '../../model/SignUpRequest';

@Component({
    selector: 'app-register',
    templateUrl: './register.page.html',
    styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

    signUpRequest: SignUpRequest;
    success = '';


    constructor(private authenticationService: AuthenticationService) {
        this.signUpRequest = new SignUpRequest();
    }

    ngOnInit() {
    }

    register() {
        this.success = '';
        this.authenticationService.signUp(this.signUpRequest)
            .subscribe(result => {
                if (result.success) {
                    console.log('siker');
                    this.success = 'Sikeres regisztrálás!';
                }
            }, error => {
                console.log('error');
            });
    }

}
