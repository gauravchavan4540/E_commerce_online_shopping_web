import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowproductsComponent } from './showproducts.component';

describe('ShowproductsComponent', () => {
  let component: ShowproductsComponent;
  let fixture: ComponentFixture<ShowproductsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowproductsComponent]
    });
    fixture = TestBed.createComponent(ShowproductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
