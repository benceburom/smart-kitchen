import {Component} from '@angular/core';
import {RegisterPage} from '../authentication/register/register.page';

@Component({
    selector: 'app-tabs',
    templateUrl: 'tabs.page.html',
    styleUrls: ['tabs.page.scss']
})
export class TabsPage {
    registerPage = RegisterPage;

    constructor() {
    }
}
