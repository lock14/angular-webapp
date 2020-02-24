package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.api.Person;
import org.lock14.angularwebapp.persistence.PersonEntity;
import org.lock14.angularwebapp.mapper.PersonMapper;
import org.lock14.angularwebapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends ApiMappingService<PersonRepository, Person, PersonEntity, Long> {
    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper,
                         PersonSpecificationGenerator personSpecificationGenerator) {
        super(personRepository, personMapper, personSpecificationGenerator);
    }
}
