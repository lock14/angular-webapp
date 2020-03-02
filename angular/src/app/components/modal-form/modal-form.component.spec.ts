import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ModalFormComponent} from './modal-form.component';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {ReactiveFormsModule} from '@angular/forms';

describe('ModalFormComponent', () => {
  let component: ModalFormComponent<any>;
  let fixture: ComponentFixture<ModalFormComponent<any>>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        ModalFormComponent,
      ],
      imports: [
        MatDialogModule,
        MatFormFieldModule,
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
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
