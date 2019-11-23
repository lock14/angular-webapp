import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PagingRestTableComponent } from './paging-rest-table.component';

describe('PagingRestTableComponent', () => {
  let component: PagingRestTableComponent;
  let fixture: ComponentFixture<PagingRestTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PagingRestTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PagingRestTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
