import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {PagingRestService} from '../../services/paging-rest.service';
import {Person} from '../../models/person';
import {PagingTableComponent} from '../../components/paging-table/paging-table.component';
import {MatDialog} from '@angular/material/dialog';
import {filter, flatMap} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {FieldDefinition} from '../../models/field-definition';
import {SearchCriteria} from '../../models/search-criteria';
import {ModalFormComponent} from '../../components/modal-form/modal-form.component';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-person',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.scss']
})
export class PeopleComponent implements OnInit {
  readonly fieldDefinitions: FieldDefinition[] = [
    {name: 'id', displayName: 'ID', inputType: 'number'},
    {name: 'firstName', displayName: 'First Name', inputType: 'text'},
    {name: 'lastName', displayName: 'Last Name', inputType: 'text'},
    {name: 'addressId', displayName: 'Address ID', inputType: 'number'},
  ];

  @ViewChild(PagingTableComponent, {static: false}) personTable: PagingTableComponent<Person>;

  constructor(@Inject('personService') readonly personService: PagingRestService<Person>,
              private dialog: MatDialog,
              public snackBar: MatSnackBar) {
  }

  public ngOnInit(): void {
  }

  public search(person: Person): void {
    const searchCriteria: SearchCriteria = {};
    Object.entries(person)
      .filter(entry => entry[1] !== null && entry[1] !== undefined)
      .forEach(entry => searchCriteria[entry[0]] = [entry[1]]);
    this.personTable.search(searchCriteria);
  }

  public add() {
    const requiredFields = ['firstName', 'lastName'];
    this.dialog.open(ModalFormComponent, {
      data: {
        title: 'Add',
        fieldDefinitions: this.fieldDefinitions,
        required: requiredFields,
        hidden: ['id', 'addressId'],
        disabled: ['id', 'addressId']
      }
    })
      .afterClosed()
      .pipe(
        filter(person => person != null),
        filter(person =>
          requiredFields.map(field => person[field] != null)
            .reduce((b1, b2) => b1 && b2, true)),
        flatMap(result => this.personService.create(result))
      ).subscribe(
      person => this.snackBar.open('Add Successful', 'OK'),
      error => this.snackBar.open('Error', 'OK')
    );
  }

  public editFn(): (value: any) => Observable<Person> {
    return value => {
      const requiredFields = ['firstName', 'lastName'];
      return this.dialog.open(ModalFormComponent, {
        data: {
          title: 'Edit',
          model: value,
          fieldDefinitions: this.fieldDefinitions,
          required: requiredFields,
          hidden: ['id', 'addressId'],
          disabled: ['id', 'addressId']
        }
      })
        .afterClosed()
        .pipe(
          filter(person => person != null),
          filter(person =>
            requiredFields.map(field => person[field] != null)
              .reduce((b1, b2) => b1 && b2, true)),
          flatMap(result => {
            return this.personService.update(result.id, result);
          })
        );
    }
  }
}
