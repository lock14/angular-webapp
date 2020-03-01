import {Person} from './person';
import {Address} from './address';

export class PersonAddress {
  id: number;
  person: Person;
  address: Address;

  constructor(person, address) {
    this.id = person.id;
    this.person = person;
    this.address = address;
  }
}
