import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatagorymgmtComponent } from './catagorymgmt.component';

describe('CatagorymgmtComponent', () => {
  let component: CatagorymgmtComponent;
  let fixture: ComponentFixture<CatagorymgmtComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CatagorymgmtComponent]
    });
    fixture = TestBed.createComponent(CatagorymgmtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
