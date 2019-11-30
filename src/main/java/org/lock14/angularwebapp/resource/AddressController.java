package org.lock14.angularwebapp.resource;

import org.lock14.angularwebapp.api.ApiAddress;
import org.lock14.angularwebapp.api.ApiPage;
import org.lock14.angularwebapp.service.AddressService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.lock14.angularwebapp.AppConstants.API_URI;

@RestController
@RequestMapping(API_URI + "/addresses")
public class AddressController extends PagingRestController<ApiAddress, Long> {

    @Autowired
    public AddressController(AddressService addressService) {
        super(addressService);
    }
}
