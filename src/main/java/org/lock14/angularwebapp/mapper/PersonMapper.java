package org.lock14.angularwebapp.mapper;

import org.lock14.angularwebapp.api.ApiPerson;
import org.lock14.angularwebapp.domain.Address;
import org.lock14.angularwebapp.domain.Person;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PersonMapper extends ApiMapper<ApiPerson, Person> {

    @Override
    @Mapping(source = "addressId", target = "address")
    Person fromApi(ApiPerson apiPerson);

    @Override
    @InheritInverseConfiguration
    ApiPerson toApi(Person person);

    default Address idToAddress(Long id) {
        Address address = new Address();
        address.setId(id);
        return address;
    }

    default Long addressToId(Address address) {
        return address.getId();
    }
}
