import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyInquiryComponent } from './my-inquiry.component';

describe('MyInquiryComponent', () => {
  let component: MyInquiryComponent;
  let fixture: ComponentFixture<MyInquiryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyInquiryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyInquiryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
