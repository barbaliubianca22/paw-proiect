import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedExploringComponent } from './logged-exploring.component';

describe('LoggedExploringComponent', () => {
  let component: LoggedExploringComponent;
  let fixture: ComponentFixture<LoggedExploringComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoggedExploringComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoggedExploringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
