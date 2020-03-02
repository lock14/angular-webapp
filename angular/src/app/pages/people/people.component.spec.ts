import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PeopleComponent} from './people.component';
import {PagingTableComponent} from '../../components/paging-table/paging-table.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatFormFieldModule} from '@angular/material/form-field';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {PagingRestService} from '../../services/paging-rest.service';
import {Person} from '../../models/person';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MatInputModule} from '@angular/material/input';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatSortModule} from '@angular/material/sort';
import {ModalFormComponent} from '../../components/modal-form/modal-form.component';
import {MatIconModule} from '@angular/material/icon';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBarModule} from '@angular/material/snack-bar';

describe('PeopleComponent', () => {
  let component: PeopleComponent;
  let fixture: ComponentFixture<PeopleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        PagingTableComponent,
        PeopleComponent,
        ModalFormComponent
      ],
      imports: [
        NoopAnimationsModule,
        HttpClientTestingModule,
        MatFormFieldModule,
        MatDialogModule,
        MatIconModule,
        MatInputModule,
        MatPaginatorModule,
        MatProgressSpinnerModule,
        MatSnackBarModule,
        MatSortModule,
        MatTableModule,
        ReactiveFormsModule
      ],
      providers: [
        {
          provide: MatDialogRef,
          useFactory: () => null
        },
        {
          provide: MAT_DIALOG_DATA,
          useFactory: () => null
        },
        {
          provide: 'personService',
          deps: [HttpClient],
          useFactory: httpClient => new PagingRestService<Person>(httpClient, 'api/people')
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PeopleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
