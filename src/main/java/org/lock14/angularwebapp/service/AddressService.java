package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.api.Address;
import org.lock14.angularwebapp.persistence.AddressEntity;
import org.lock14.angularwebapp.mapper.AddressMapper;
import org.lock14.angularwebapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends ApiMappingService<AddressRepository, Address, AddressEntity, Long> {
    @Autowired
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper,
                          AddressSpecificationGenerator addressSpecificationGenerator) {
        super(addressRepository, addressMapper, addressSpecificationGenerator);
    }
}
