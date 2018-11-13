import {Component} from '@angular/core';

import {Platform, AlertController} from '@ionic/angular';
import {SplashScreen} from '@ionic-native/splash-screen/ngx';
import {StatusBar} from '@ionic-native/status-bar/ngx';

@Component({
    selector: 'app-root',
    templateUrl: 'app.component.html'
})
export class AppComponent {
    constructor(
        private platform: Platform,
        private splashScreen: SplashScreen,
        private statusBar: StatusBar,
        public alertController: AlertController
    ) {
        this.initializeApp();
        this.backButtonEvent();
    }

    initializeApp() {
        this.platform.ready().then(() => {
            this.statusBar.styleDefault();
            this.splashScreen.hide();
        });
    }

    backButtonEvent() {
        this.platform.backButton.subscribe(() => {
            this.presentAlert();
        });
    }

    async presentAlert() {
        const alert = await this.alertController.create({
          header: 'Exit app',
          message: 'Do you want to exit the application?',
          buttons: [{
            text: 'Cancel',
            role: 'cancel',
            cssClass: 'secondary',
            handler: () => {
              alert.dismiss();
            }
          }, {
            text: 'Ok',
            handler: () => {
                navigator['app'].exitApp();
            }
          }]
        });
        await alert.present();
      }
}
