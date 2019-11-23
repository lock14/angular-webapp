import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {PagingRestService} from '../../service/paging-rest.service';
import {Person} from '../../models/person';
import {PagingRestTableComponent} from '../../components/paging-rest-table/paging-rest-table.component';

@Component({
  selector: 'app-person',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.scss']
})
export class PeopleComponent implements OnInit {
  readonly columns = [
    {field: 'firstName', header: 'First Name'},
    {field: 'lastName', header: 'Last Name'},
  ];

  @ViewChild(PagingRestTableComponent, {static: false}) personTable: PagingRestTableComponent<Person>;

  constructor(@Inject('personService') readonly personService: PagingRestService<Person>) { }

  public ngOnInit(): void {
  }

  public search(person: Person): void {
    this.personTable.search({
      firstName: [person.firstName],
      lastName: [person.lastName]
    });
  }
}
