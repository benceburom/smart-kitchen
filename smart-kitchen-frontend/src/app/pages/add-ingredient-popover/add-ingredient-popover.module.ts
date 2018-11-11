import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { AddIngredientPopoverPage } from './add-ingredient-popover.page';

const routes: Routes = [
  {
    path: '',
    component: AddIngredientPopoverPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [AddIngredientPopoverPage]
})
export class AddIngredientPopoverPageModule {}
