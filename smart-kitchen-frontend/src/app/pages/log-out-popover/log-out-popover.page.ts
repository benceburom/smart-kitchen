import { Component, OnInit } from '@angular/core';
import { PopoverController, NavController } from '@ionic/angular';
import { AuthenticationService } from '../../services/authentication.service';
import { Toast } from '../../toast/toast';

@Component({
  selector: 'app-log-out-popover',
  templateUrl: './log-out-popover.page.html',
  styleUrls: ['./log-out-popover.page.scss'],
})
export class LogOutPopoverPage implements OnInit {

  constructor(private popoverCtrl: PopoverController,
    private authService: AuthenticationService,
    private navCtrl: NavController,
    private toast: Toast) { }

  ngOnInit() {
  }

  async onLogOut() {
    try {
      this.authService.logout();
      await this.popoverCtrl.dismiss();
      this.toast.presentToastWithOptions({
        message: 'Logout succesful',
        duration: 2000,
        showCloseButton: true,
        position: 'bottom',
        color: 'success',
        closeButtonText: 'Close'
      });
      this.navCtrl.navigateRoot(``);
    } catch (e) {
    }

  }

}
