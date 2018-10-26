import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

const routes: Routes = [
    {path: '', loadChildren: './pages/home/home.module#HomePageModule'},
    {path: 'register', loadChildren: './pages/register/register.module#RegisterPageModule'},
    {path: 'logged-in-user', loadChildren: './pages/logged-in-user/logged-in-user.module#LoggedInUserPageModule'}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
