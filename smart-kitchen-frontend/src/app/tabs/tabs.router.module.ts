import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {TabsPage} from './tabs.page';
import {HomePage} from '../pages/home/home.page';
import {RegisterPage} from '../pages/register/register.page';
import {LoggedInUserPage} from '../pages/logged-in-user/logged-in-user.page';

const routes: Routes = [
    {
        path: 'tabs',
        component: TabsPage,
        children: [
            {
                path: '',
                redirectTo: '/tabs/(home:home)',
                pathMatch: 'full',
            },
            {
                path: 'home',
                outlet: 'home',
                component: HomePage
            },
            {
                path: 'register',
                outlet: 'register',
                component: RegisterPage
            },
            {
                path: 'logged-in-user',
                outlet: 'logged-in-user',
                component: LoggedInUserPage
            }
        ]
    },
    {
        path: '',
        redirectTo: '/tabs/(home:home)',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class TabsPageRoutingModule {
}
