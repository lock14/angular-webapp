package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.repository.PersonRepository;
import org.lock14.angularwebapp.repository.PersonSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Page<Person> findAll(PersonSearchCriteria criteria, Pageable pageable) {
        return personRepository.findAll(criteria.toSpecification(), pageable);
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
