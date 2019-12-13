import { TestBed } from '@angular/core/testing';

import { PersonAddressService } from './person-address.service';

describe('PersonAddressService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PersonAddressService = TestBed.get(PersonAddressService);
    expect(service).toBeTruthy();
  });
});
