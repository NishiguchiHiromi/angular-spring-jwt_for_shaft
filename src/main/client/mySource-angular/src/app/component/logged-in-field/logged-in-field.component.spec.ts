import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedInFieldComponent } from './logged-in-field.component';

describe('LoggedInFieldComponent', () => {
  let component: LoggedInFieldComponent;
  let fixture: ComponentFixture<LoggedInFieldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoggedInFieldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoggedInFieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
