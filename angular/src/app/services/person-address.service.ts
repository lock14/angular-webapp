import {Inject, Injectable} from '@angular/core';
import {PagingRestService} from './paging-rest.service';
import {Person} from '../models/person';
import {Address} from '../models/address';
import {PagingService} from './paging-service';
import {PersonAddress} from '../models/person-address';
import {forkJoin, Observable} from 'rxjs';
import {PagingParams} from '../models/paging-params';
import {Page} from '../models/page';
import {catchError, flatMap, map} from 'rxjs/operators';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonAddressService implements PagingService<PersonAddress> {

  constructor(@Inject('personService') readonly personService: PagingRestService<Person>,
              @Inject('addressService') readonly addressService: PagingRestService<Address>) {
  }

  public findAll(pagingParams: PagingParams): Observable<Page<PersonAddress>> {
    return this.personService.findAll(pagingParams)
      .pipe(
        flatMap(page => page.flatMap(person => this.toPersonAddress(person)))
      );
  }

  public get(id: string | number): Observable<PersonAddress> {
    return this.personService.get(id)
      .pipe(
        flatMap(person => this.toPersonAddress(person))
      );
  }

  public create(data: PersonAddress): Observable<PersonAddress> {
    const addressObservable: Observable<Address> = this.addressService.get(data.address.id)
      .pipe(
        catchError(err => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === 404) {
              return this.addressService.create(data.address);
            }
          }
          throw err;
        })
      );
    return forkJoin([this.personService.create(data.person), addressObservable])
      .pipe(
        map((data: [Person, Address]) => new PersonAddress(data[0], data[1]))
      );
  }

  public update(id: string | number, data: PersonAddress): Observable<PersonAddress> {
    const addressObservable: Observable<Address> = this.addressService.update(data.address.id, data.address);
    const personObservable: Observable<Person> = this.personService.update(data.person.id, data.person);
    return forkJoin([personObservable, addressObservable])
      .pipe(
        map((data: [Person, Address]) => new PersonAddress(data[0], data[1]))
      );
  }

  public delete(id: string | number): Observable<void> {
    return this.personService.delete(id);
  }

  private toPersonAddress(person: Person): Observable<PersonAddress> {
    return this.addressService.get(person.addressId)
      .pipe(
        map(address => new PersonAddress(person, address))
      );
  }
}
