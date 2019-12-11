package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.api.ApiAddress;
import org.lock14.angularwebapp.domain.Address;
import org.lock14.angularwebapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AddressService extends ApiConverterService<AddressRepository, ApiAddress, Address, Long> {
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        super(addressRepository, Address::fromApi);
    }

    @Override
    protected void initFieldMap() {
        apiFieldsToEntityFields.putAll(
                Map.of("id", "id",
                       "streetAddress", "streetAddress",
                       "city", "city",
                       "state", "state",
                       "zipCode", "zipCode",
                       "personId", "person_id")
        );
    }
}
