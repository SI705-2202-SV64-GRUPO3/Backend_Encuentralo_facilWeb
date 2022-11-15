import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoresComparationComponent } from './stores-comparation.component';

describe('StoresComparationComponent', () => {
  let component: StoresComparationComponent;
  let fixture: ComponentFixture<StoresComparationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StoresComparationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StoresComparationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
