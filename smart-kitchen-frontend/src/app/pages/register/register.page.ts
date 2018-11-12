import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services/authentication.service';
import { SignUpRequest } from '../../model/SignUpRequest';
import { NavController } from '@ionic/angular';
import { Toast } from '../../toast/toast';

@Component({
    selector: 'app-register',
    templateUrl: './register.page.html',
    styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

    signUpRequest: SignUpRequest;


    constructor(private authenticationService: AuthenticationService, private navCtrl: NavController, private toast: Toast) {
        this.signUpRequest = new SignUpRequest();
    }

    ngOnInit() {
    }

    register() {
        this.authenticationService.signUp(this.signUpRequest)
            .subscribe(result => {
                if (result.success) {
                    this.toast.presentToastWithOptions({
                        message: 'Register success',
                        showCloseButton: true,
                        position: 'bottom',
                        closeButtonText: 'Close',
                        duration: 2000
                    });
                    this.navCtrl.navigateRoot(``);
                }
            }, error => {
                let myMessage = '';
                console.log(error);
                if (error.error.errors) {
                    myMessage = 'Invalid input';
                } else { myMessage = error.error.message; }
                this.toast.presentToastWithOptions({
                    message: myMessage,
                    showCloseButton: true,
                    position: 'bottom',
                    closeButtonText: 'Close',
                    duration: 2000
                });
            });
    }

}
