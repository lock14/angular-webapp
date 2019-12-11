package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.api.ApiPerson;
import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.domain.Person_;
import org.lock14.angularwebapp.repository.SearchCriterion;
import org.lock14.angularwebapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonService extends ApiConverterService<PersonRepository, ApiPerson, Person, Long> {
    @Autowired
    public PersonService(PersonRepository personRepository) {
        super(personRepository, Person::fromApi);
    }

    @Override
    protected void initFieldMap() {
        apiFieldsToEntityFields.putAll(
                Map.of("id", "id",
                       "firstName", "firstName",
                       "lastName", "lastName")
        );
    }
}
