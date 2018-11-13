import { Injectable } from '@angular/core';
import { CanDeactivate, Router, ActivatedRouteSnapshot } from '@angular/router';
import { ModalController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class BackButtonService implements CanDeactivate<any> {

  constructor(
    private modalCtrl: ModalController,
    private readonly location: Location,
    private readonly router: Router) { }

  canDeactivate(component: any, currentRoute: ActivatedRouteSnapshot): Promise<boolean> {
    console.log('canDeactivate called');
    return new Promise((resolve) => {
      this.modalCtrl.getTop()
        .then((element) => {
          if (element) {
            console.log(element);
            element.dismiss();
            const currentUrlTree = this.router.createUrlTree([currentRoute]);
            const currentUrl = currentUrlTree.toString();
            this.location.replace(currentUrl);
            //   go(currentUrl);
            resolve(false);
          } else {
            resolve(true);
          }
        });
    });
  }
}
