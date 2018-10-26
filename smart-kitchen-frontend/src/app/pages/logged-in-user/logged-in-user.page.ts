import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-logged-in-user',
    templateUrl: './logged-in-user.page.html',
    styleUrls: ['./logged-in-user.page.scss'],
})
export class LoggedInUserPage implements OnInit {

    constructor() {
    }

    ngOnInit() {
        console.log('im here');
    }

}
