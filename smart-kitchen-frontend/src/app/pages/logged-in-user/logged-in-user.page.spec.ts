import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LoggedInUserPage} from './logged-in-user.page';

describe('LoggedInUserPage', () => {
    let component: LoggedInUserPage;
    let fixture: ComponentFixture<LoggedInUserPage>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [LoggedInUserPage],
            schemas: [CUSTOM_ELEMENTS_SCHEMA],
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(LoggedInUserPage);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
