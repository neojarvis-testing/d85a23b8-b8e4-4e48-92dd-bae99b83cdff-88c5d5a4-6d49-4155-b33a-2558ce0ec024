import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminControlPanelComponent } from './admin-control-panel.component';

describe('AdminControlPanelComponent', () => {
  let component: AdminControlPanelComponent;
  let fixture: ComponentFixture<AdminControlPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminControlPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminControlPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
