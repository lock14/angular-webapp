import { TestBed } from '@angular/core/testing';

import { PagingRestService } from './paging-rest.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {HttpClient} from '@angular/common/http';
import {Person} from '../models/person';

describe('PagingRestService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule
    ],
    providers: [
      {
        provide: 'testService',
        deps: [HttpClient],
        useFactory: httpClient => new PagingRestService<unknown>(httpClient, 'api/unknown')
      }
    ]
  }));

  it('should be created', () => {
    const service: PagingRestService<unknown> = TestBed.get('testService');
    expect(service).toBeTruthy();
  });
});
