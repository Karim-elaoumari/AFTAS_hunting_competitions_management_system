import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishesComponent } from './fishes.component';

describe('FishesComponent', () => {
  let component: FishesComponent;
  let fixture: ComponentFixture<FishesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FishesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FishesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
