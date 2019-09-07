import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseDoctorComponent } from './choose-doctor.component';

describe('ChooseDoctorComponent', () => {
  let component: ChooseDoctorComponent;
  let fixture: ComponentFixture<ChooseDoctorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseDoctorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseDoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
