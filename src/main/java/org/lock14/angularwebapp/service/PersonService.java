package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.api.ApiPerson;
import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.domain.Person_;
import org.lock14.angularwebapp.repository.SearchCriterion;
import org.lock14.angularwebapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService extends ApiConverterService<PersonRepository, ApiPerson, Person, Long> {

    @Autowired
    public PersonService(PersonRepository personRepository) {
        super(personRepository, Person::fromApi);
    }

    @Override
    public Specification<Person> toSpecification(MultiValueMap<String, String> filters) {
        return SearchCriterion.<Person>in(Person_.ID, filters.get(Person_.ID))
                              .and(SearchCriterion.in(Person_.FIRST_NAME, filters.get(Person_.FIRST_NAME)))
                              .and(SearchCriterion.in(Person_.LAST_NAME, filters.get(Person_.LAST_NAME)));
    }
}
