import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Person} from '../../models/person';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-person-form',
  templateUrl: './person-form.component.html',
  styleUrls: ['./person-form.component.scss']
})
export class PersonFormComponent implements OnInit {
  @Input() submitText = 'Submit';
  @Output() personEmitter = new EventEmitter<Person>();
  form: FormGroup;

  constructor() { }

  ngOnInit() {
    this.form = new FormGroup({
      firstName: new FormControl(),
      lastName: new FormControl(),
    });
  }

  public submit(): void {
    this.personEmitter.emit(this.form.value);
  }

  public clear(): void {
    this.form.reset();
  }
}
