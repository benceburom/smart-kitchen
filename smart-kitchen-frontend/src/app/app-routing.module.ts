import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

const routes: Routes = [
    {path: '', loadChildren: './pages/home/home.module#HomePageModule'},
    {path: 'register', loadChildren: './pages/register/register.module#RegisterPageModule'},
    {path: 'logged-in-user/:id', loadChildren: './pages/logged-in-user/logged-in-user.module#LoggedInUserPageModule'},
    {path: 'kitchen-detail/:id', loadChildren: './pages/kitchen-detail/kitchen-detail.module#KitchenDetailPageModule'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
