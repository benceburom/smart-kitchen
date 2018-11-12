import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { AddFoodPopoverPage } from './add-food-popover.page';

const routes: Routes = [
  {
    path: '',
    component: AddFoodPopoverPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [AddFoodPopoverPage]
})
export class AddFoodPopoverPageModule {}
