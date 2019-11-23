import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PagingTableComponent } from './paging-table.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatSortModule} from '@angular/material/sort';

describe('PagingRestTableComponent', () => {
  let component: PagingTableComponent<unknown>;
  let fixture: ComponentFixture<PagingTableComponent<unknown>>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PagingTableComponent ],
      imports: [
        MatPaginatorModule,
        MatProgressSpinnerModule,
        MatSortModule,
        MatTableModule,
        NoopAnimationsModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PagingTableComponent);
    component = fixture.componentInstance;
    component.columns = [];
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
