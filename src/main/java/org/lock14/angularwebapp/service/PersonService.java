package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.api.ApiPerson;
import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.mapper.PersonMapper;
import org.lock14.angularwebapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends ApiConverterService<PersonRepository, ApiPerson, Person, Long> {
    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper,
                         PersonSpecificationGenerator personSpecificationGenerator) {
        super(personRepository, personMapper, personSpecificationGenerator);
    }
}
