import { Component, OnInit } from '@angular/core';
import { PopoverController, NavController } from '@ionic/angular';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-log-out-popover',
  templateUrl: './log-out-popover.page.html',
  styleUrls: ['./log-out-popover.page.scss'],
})
export class LogOutPopoverPage implements OnInit {

  constructor(private popoverCtrl: PopoverController, private authService: AuthenticationService, private navCtrl: NavController) { }

  ngOnInit() {
  }

  async onLogOut() {
    try {
      this.authService.logout();
      await this.popoverCtrl.dismiss();
      this.navCtrl.navigateRoot(``);
    } catch (e) {
    }

  }

}
