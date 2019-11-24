package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.domain.Person_;
import org.lock14.angularwebapp.repository.PersonRepository;
import org.lock14.angularwebapp.repository.SpecificationBuilder;
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

    public Page<Person> findAll(Set<Long> ids, Set<String> firstNames, Set<String> lastNames, Pageable pageable) {
        Specification<Person> spec = new SpecificationBuilder<Person>()
                .withField(Person_.id)
                .in(ids)
                .withField(Person_.firstName)
                .in(firstNames)
                .withField(Person_.lastName)
                .in(lastNames)
                .build();
        return personRepository.findAll(spec, pageable);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }
}
