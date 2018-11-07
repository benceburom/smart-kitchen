import {IonicModule} from '@ionic/angular';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

import {TabsPageRoutingModule} from './tabs.router.module';

import {TabsPage} from './tabs.page';
import {HomePageModule} from '../pages/home/home.module';
import {RegisterPageModule} from '../pages/register/register.module';
import {LoggedInUserPageModule} from '../pages/logged-in-user/logged-in-user.module';
import {KitchenDetailPageModule} from '../pages/kitchen-detail/kitchen-detail.module';
import {WishListPageModule} from '../pages/wish-list/wish-list.module';
import {FoodDetailPageModule} from '../pages/food-detail/food-detail.module';

@NgModule({
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        TabsPageRoutingModule,
        HomePageModule,
        RegisterPageModule,
        LoggedInUserPageModule,
        KitchenDetailPageModule,
        WishListPageModule,
        FoodDetailPageModule
    ],
    declarations: [TabsPage]
})
export class TabsPageModule {
}
