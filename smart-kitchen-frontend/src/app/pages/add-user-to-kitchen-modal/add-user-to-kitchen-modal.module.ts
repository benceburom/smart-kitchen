import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { AddUserToKitchenModalPage } from './add-user-to-kitchen-modal.page';

const routes: Routes = [
  {
    path: '',
    component: AddUserToKitchenModalPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [AddUserToKitchenModalPage]
})
export class AddUserToKitchenModalPageModule {}
