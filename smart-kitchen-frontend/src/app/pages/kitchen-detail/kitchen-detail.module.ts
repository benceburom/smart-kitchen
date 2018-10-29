import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Routes, RouterModule} from '@angular/router';

import {IonicModule} from '@ionic/angular';

import {KitchenDetailPage} from './kitchen-detail.page';

const routes: Routes = [
    {
        path: '',
        component: KitchenDetailPage
    }
];

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        RouterModule.forChild(routes)
    ],
    declarations: [KitchenDetailPage]
})
export class KitchenDetailPageModule {
}
