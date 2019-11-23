package org.lock14.angularwebapp.resource;

import org.lock14.angularwebapp.domain.PageDTO;
import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.repository.PersonSearchCriteria;
import org.lock14.angularwebapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/test")
    public String getTes() {
        return "It Worked";
    }

    @GetMapping("/people")
    public ResponseEntity<PageDTO<Person>> findAll(
            @RequestParam(required = false) Set<String> firstName,
            @RequestParam(required = false) Set<String> lastName,
            @RequestParam(required = false) String firstNameLike,
            @RequestParam(required = false) String lastNameLike,
            Pageable pageable) {
        PersonSearchCriteria criteria = PersonSearchCriteria.builder()
                                                            .withFirstNames(firstName)
                                                            .withLastNames(lastName)
                                                            .withFirstNameLike(firstNameLike)
                                                            .withLastNameLike(lastNameLike)
                                                            .build();
        return ResponseEntity.ok(PageDTO.of(personService.findAll(criteria, pageable)));
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> get(Long id) {
        return ResponseEntity.ok(personService.findById(id)
                                              .orElseThrow(PersonController::notFoundException));
    }

    @PostMapping("/people")
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.personService.save(person));
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
        return ResponseEntity.ok(
                this.personService.save(this.personService.findById(id)
                                                          .map(p -> p.setFirstName(person.getFirstName()))
                                                          .map(p -> p.setLastName(person.getLastName()))
                                                          .orElseThrow(PersonController::notFoundException))
        );
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private static ResponseStatusException notFoundException() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "No Such Person");
    }
}
