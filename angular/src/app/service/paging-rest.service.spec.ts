import { TestBed } from '@angular/core/testing';

import { PagingRestService } from './paging-rest.service';

describe('PagingRestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PagingRestService = TestBed.get(PagingRestService);
    expect(service).toBeTruthy();
  });
});
