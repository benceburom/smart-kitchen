import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { LogOutPopoverPage } from './log-out-popover.page';

const routes: Routes = [
  {
    path: '',
    component: LogOutPopoverPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [LogOutPopoverPage]
})
export class LogOutPopoverPageModule {}
