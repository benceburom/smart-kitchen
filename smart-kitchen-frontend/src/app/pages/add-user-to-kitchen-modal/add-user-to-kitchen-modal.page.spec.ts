import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUserToKitchenModalPage } from './add-user-to-kitchen-modal.page';

describe('AddUserToKitchenModalPage', () => {
  let component: AddUserToKitchenModalPage;
  let fixture: ComponentFixture<AddUserToKitchenModalPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddUserToKitchenModalPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddUserToKitchenModalPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
