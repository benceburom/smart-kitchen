import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Routes, RouterModule} from '@angular/router';

import {IonicModule} from '@ionic/angular';

import {LoggedInUserPage} from './logged-in-user.page';

const routes: Routes = [
    {
        path: '',
        component: LoggedInUserPage
    }
];

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        RouterModule.forChild(routes)
    ],
    declarations: [LoggedInUserPage]
})
export class LoggedInUserPageModule {
}
