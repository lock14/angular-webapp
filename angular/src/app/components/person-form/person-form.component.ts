import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {Person} from '../../models/person';
import {FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-person-form',
  templateUrl: './person-form.component.html',
  styleUrls: ['./person-form.component.scss']
})
export class PersonFormComponent implements OnInit {
  @Input() submitText = 'Submit';
  @Input() title = '';
  @Input() required: string[] = [];
  @Input() person: Person = {id: null, firstName: null, lastName: null, addressId: null };
  @Output() personEmitter = new EventEmitter<Person>();
  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<PersonFormComponent>,
              @Inject(MAT_DIALOG_DATA) data) {
    if (data) {
      if (data.title) {
        this.title = data.title;
      }
      if (data.submitText) {
        this.submitText = data.submitText;
      }
      if (data.required) {
        this.required = data.required;
      }
    }
  }

  ngOnInit() {
    const requiredFields: Set<String> = new Set<String>(this.required);
    const firstNameValidators: ValidatorFn[] = this.getValidators(requiredFields, 'firstName');
    const lastNameValidators: ValidatorFn[] = this.getValidators(requiredFields, 'lastName');
    this.form = new FormGroup({
      firstName: new FormControl(this.person.firstName, firstNameValidators),
      lastName: new FormControl(this.person.lastName, lastNameValidators),
    });
  }

  public submit(): void {
    if (this.dialogRef) {
      this.dialogRef.close(this.form.value);
    } else {
      this.personEmitter.emit(this.form.value);
    }
  }

  public clear(): void {
    this.form.reset();
  }

  public close(): void {
    if (this.dialogRef) {
      this.dialogRef.close({});
    }
  }

  private getValidators(requiredFields: Set<String>, field: string): ValidatorFn[] {
    const validators: ValidatorFn[] = [];
    if (requiredFields.has(field)) {
      validators.push(Validators.required);
    }
    return validators;
  }
}
