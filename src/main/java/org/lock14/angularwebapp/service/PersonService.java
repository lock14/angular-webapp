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

import java.util.Optional;
import java.util.Set;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Page<ApiPerson> findAll(Set<Long> ids, Set<String> firstNames, Set<String> lastNames, Pageable pageable) {
        Specification<Person> spec = SearchCriterion.in(Person_.id, ids)
                                                    .and(SearchCriterion.in(Person_.firstName, firstNames))
                                                    .and(SearchCriterion.in(Person_.lastName, lastNames));
        return personRepository.findAll(spec, pageable)
                               .map(Person::toApi);
    }

    public Optional<ApiPerson> findById(Long id) {
        return personRepository.findById(id)
                               .map(Person::toApi);
    }

    public ApiPerson save(ApiPerson person) {
        return personRepository.save(Person.fromApi(person))
                               .toApi();
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
