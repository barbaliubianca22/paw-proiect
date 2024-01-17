import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StartExploringComponent } from './start-exploring.component';

describe('StartExploringComponent', () => {
  let component: StartExploringComponent;
  let fixture: ComponentFixture<StartExploringComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StartExploringComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StartExploringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
