package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.api.ApiAddress;
import org.lock14.angularwebapp.domain.Address;
import org.lock14.angularwebapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends ApiConverterService<AddressRepository, ApiAddress, Address, Long> {
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        super(addressRepository, Address::fromApi);
    }
}
