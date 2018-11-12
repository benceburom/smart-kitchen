import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    { path: '', loadChildren: './pages/home/home.module#HomePageModule' },
    { path: 'register', loadChildren: './pages/register/register.module#RegisterPageModule' },
    { path: 'logged-in-user/:id', loadChildren: './pages/logged-in-user/logged-in-user.module#LoggedInUserPageModule' },
    { path: 'kitchen-detail/:id', loadChildren: './pages/kitchen-detail/kitchen-detail.module#KitchenDetailPageModule' },
    { path: 'wish-list/:id', loadChildren: './pages/wish-list/wish-list.module#WishListPageModule' },
    { path: 'food-detail/:id', loadChildren: './pages/food-detail/food-detail.module#FoodDetailPageModule' },
    { path: 'log-out-popover', loadChildren: './pages/log-out-popover/log-out-popover.module#LogOutPopoverPageModule' },
    { path: 'add-ingredient-popover',
     loadChildren: './pages/add-ingredient-popover/add-ingredient-popover.module#AddIngredientPopoverPageModule' },
  { path: 'add-food-popover', loadChildren: './pages/add-food-popover/add-food-popover.module#AddFoodPopoverPageModule' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
