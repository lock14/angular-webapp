import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {PagingRestService} from '../../services/paging-rest.service';
import {Person} from '../../models/person';
import {PagingTableComponent} from '../../components/paging-table/paging-table.component';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {PersonFormComponent} from '../../components/person-form/person-form.component';
import {filter, flatMap} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-person',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.scss']
})
export class PeopleComponent implements OnInit {
  readonly columns = [
    {field: 'id', header: 'ID'},
    {field: 'firstName', header: 'First Name'},
    {field: 'lastName', header: 'Last Name'},
  ];

  @ViewChild(PagingTableComponent, {static: false}) personTable: PagingTableComponent<Person>;

  constructor(@Inject('personService') readonly personService: PagingRestService<Person>,
              private dialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

  public ngOnInit(): void {
  }

  public search(person: Person): void {
    this.personTable.search({
      firstName: [person.firstName],
      lastName: [person.lastName]
    });
  }

  public add() {
    this.dialog.open(PersonFormComponent, {
      data: {
        title: 'Add',
        required: ['firstName', 'lastName']
      }
    })
      .afterClosed()
      .pipe(
        filter(person => person !== null && person !== undefined),
        filter(person => Object.entries(person).filter(entry => entry[1] !== null).length == 2),
        flatMap(result => this.personService.create(result))
      ).subscribe(
      person => this.snackBar.open('Add Successful', 'OK'),
      error => this.snackBar.open('Error', 'OK')
    );
  }
}
