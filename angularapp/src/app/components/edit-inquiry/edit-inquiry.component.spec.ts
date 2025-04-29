import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditInquiryComponent } from './edit-inquiry.component';

describe('EditInquiryComponent', () => {
  let component: EditInquiryComponent;
  let fixture: ComponentFixture<EditInquiryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditInquiryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditInquiryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
