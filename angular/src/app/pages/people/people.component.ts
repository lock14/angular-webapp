import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {PagingRestService} from '../../services/paging-rest.service';
import {Person} from '../../models/person';
import {PagingTableComponent} from '../../components/paging-table/paging-table.component';

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

  @ViewChild(PagingTableComponent, {static: false}) personTable: PagingTableComponent<Person>;

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
