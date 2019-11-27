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

@RestController
@RequestMapping("/api")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public ResponseEntity<ApiPage<ApiAddress>> findAll(Pageable pageable) {
        return ResponseEntity.ok(ApiPage.of(addressService.findAll(pageable)));
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<ApiAddress> get(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findById(id)
                                               .orElseThrow(AddressController::notFoundException));
    }

    @PostMapping("/addresses")
    public ResponseEntity<ApiAddress> create(@Valid @RequestBody ApiAddress address) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.addressService.save(address));
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<ApiAddress> update(@PathVariable Long id, @Valid @RequestBody ApiAddress address) {
        return ResponseEntity.ok(
                this.addressService.save(this.addressService.findById(id)
                                                            .map(a -> a.setStreetAddress(address.getStreetAddress()))
                                                            .map(a -> a.setCity(address.getCity()))
                                                            .map(a -> a.setState(address.getState()))
                                                            .map(a -> a.setZipCode(address.getZipCode()))
                                                            .map(a -> a.setPersonId(address.getPersonId()))
                                                            .orElseThrow(AddressController::notFoundException))
        );
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private static ResponseStatusException notFoundException() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "No Such Address");
    }
}
