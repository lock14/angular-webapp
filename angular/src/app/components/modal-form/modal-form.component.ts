import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FieldDefinition} from '../../models/field-definition';

@Component({
  selector: 'app-modal-form',
  templateUrl: './modal-form.component.html',
  styleUrls: ['./modal-form.component.scss']
})
export class ModalFormComponent<T> implements OnInit {
  @Input() title = '';
  @Input() submitText = 'Submit';
  @Input() model: any = {};
  @Input() fieldDefinitions: FieldDefinition[] = [];
  @Input() required: string[] = [];
  @Input() disabled: string[] = [];
  @Input() hidden: string[] = [];
  @Output() modelEmitter = new EventEmitter<T>();
  requiredFields: Set<string>;
  hiddenFields: Set<string>;
  disabledFields: Set<string>;
  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<ModalFormComponent<T>>,
              @Inject(MAT_DIALOG_DATA) data) {
    if (data) {
      if (data.title) {
        this.title = data.title;
      }
      if (data.submitText) {
        this.submitText = data.submitText;
      }
      if (data.model) {
        this.model = data.model;
      }
      if (data.fieldDefinitions) {
        this.fieldDefinitions = data.fieldDefinitions;
      }
      if (data.required) {
        this.required = data.required;
      }
      if (data.disabled) {
        this.disabled = data.disabled;
      }
      if (data.hidden) {
        this.hidden = data.hidden;
      }
    }
  }

  ngOnInit() {
    this.requiredFields = new Set<string>(this.required);
    this.hiddenFields = new Set<string>(this.hidden);
    this.disabledFields = new Set<string>(this.disabled);
    const controls: {[key: string]: AbstractControl} = {};
    this.fieldDefinitions.forEach(fieldDefinition => {
      const state: any = {
        value: this.model[fieldDefinition.name],
        disabled: this.disabledFields.has(fieldDefinition.name)
      };
      const validators = this.getValidators(fieldDefinition.name);
      controls[fieldDefinition.name] = new FormControl(state, validators);
    });
    this.form = new FormGroup(controls);
  }

  public submit(): void {
    if (this.dialogRef) {
      this.dialogRef.close(this.form.getRawValue());
    } else {
      this.modelEmitter.emit(this.form.getRawValue());
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

  public display(fieldName: string): string {
    if (this.hiddenFields.has(fieldName)) {
      return 'none';
    }
    return 'flex-inline';
  }

  public isDisable(fieldName: string): boolean {
    return this.disabledFields.has(fieldName);
  }

  private getValidators(field: string): ValidatorFn[] {
    const validators: ValidatorFn[] = [];
    if (this.requiredFields.has(field)) {
      validators.push(Validators.required);
    }
    return validators;
  }

}
