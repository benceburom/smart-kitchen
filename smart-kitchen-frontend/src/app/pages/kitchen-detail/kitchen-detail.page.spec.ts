import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {KitchenDetailPage} from './kitchen-detail.page';

describe('KitchenDetailPage', () => {
    let component: KitchenDetailPage;
    let fixture: ComponentFixture<KitchenDetailPage>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [KitchenDetailPage],
            schemas: [CUSTOM_ELEMENTS_SCHEMA],
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(KitchenDetailPage);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
