import {Person} from './person';
import {Address} from './address';

export class PersonAddress {
  person: Person;
  address: Address;

  constructor(person, address) {
    this.person = person;
    this.address = address;
  }
}
