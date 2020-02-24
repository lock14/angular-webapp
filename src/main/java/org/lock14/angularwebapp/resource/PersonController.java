package org.lock14.angularwebapp.resource;

import org.lock14.angularwebapp.api.Person;
import org.lock14.angularwebapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.lock14.angularwebapp.AppConstants.PEOPLE_URI;

@RestController
@RequestMapping(PEOPLE_URI)
public class PersonController extends PagingRestController<Person, Long> {

    @Autowired
    public PersonController(PersonService personService) {
        super(personService);
    }
}
