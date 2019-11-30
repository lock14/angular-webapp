package org.lock14.angularwebapp.resource;

import org.lock14.angularwebapp.api.ApiPerson;
import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.api.ApiPage;
import org.lock14.angularwebapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.lock14.angularwebapp.AppConstants.API_URI;

@RestController
@RequestMapping(API_URI + "/people")
public class PersonController extends PagingRestController<ApiPerson, Long> {

    @Autowired
    public PersonController(PersonService personService) {
        super(personService);
    }
}
