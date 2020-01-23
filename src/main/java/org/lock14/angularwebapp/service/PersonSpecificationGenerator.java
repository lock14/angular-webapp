package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.domain.Person_;
import org.lock14.angularwebapp.mapper.PersonMapper;
import org.lock14.angularwebapp.repository.SearchCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class PersonSpecificationGenerator implements SpecificationGenerator<Person> {
    private PersonMapper personMapper;

    @Autowired
    public PersonSpecificationGenerator(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public Specification<Person> generate(MultiValueMap<String, String> filters) {
        return SearchCriterion.in(Person_.id,
                                  filters.getOrDefault("id", Collections.emptyList())
                                         .stream()
                                         .map(Long::valueOf)
                                         .collect(Collectors.toList()))
                              .and(SearchCriterion.in(Person_.address,
                                                      filters.getOrDefault("addressId", Collections.emptyList())
                                                             .stream()
                                                             .map(Long::valueOf)
                                                             .map(personMapper::idToAddress)
                                                             .collect(Collectors.toList())))
                              .and(SearchCriterion.in(Person_.firstName,
                                                      filters.getOrDefault("firstName", Collections.emptyList())))
                              .and(SearchCriterion.in(Person_.lastName,
                                                      filters.getOrDefault("lastName", Collections.emptyList())));
    }
}
