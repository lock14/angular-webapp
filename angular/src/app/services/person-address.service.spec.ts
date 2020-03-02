import { TestBed } from '@angular/core/testing';

import { PersonAddressService } from './person-address.service';
import {HttpClient} from '@angular/common/http';
import {PagingRestService} from './paging-rest.service';
import {Person} from '../models/person';
import {Address} from '../models/address';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('PersonAddressService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ],
    providers: [
      {
        provide: 'personService',
        deps: [HttpClient],
        useFactory: httpClient => new PagingRestService<Person>(httpClient, 'api/people')
      },
      {
        provide: 'addressService',
        deps: [HttpClient],
        useFactory: httpClient => new PagingRestService<Address>(httpClient, 'api/addresses')
      }
    ]
  }));

  it('should be created', () => {
    const service: PersonAddressService = TestBed.get(PersonAddressService);
    expect(service).toBeTruthy();
  });
});
