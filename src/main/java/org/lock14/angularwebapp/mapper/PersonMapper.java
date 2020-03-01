package org.lock14.angularwebapp.mapper;

import org.lock14.angularwebapp.api.Person;
import org.lock14.angularwebapp.persistence.AddressEntity;
import org.lock14.angularwebapp.persistence.PersonEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PersonMapper extends ApiMapper<Person, PersonEntity> {

    @Override
    @Mapping(source = "addressId", target = "address")
    PersonEntity fromApi(Person person);

    @Override
    @InheritInverseConfiguration
    Person toApi(PersonEntity personEntity);

    default AddressEntity idToAddress(Long id) {
        if (id == null) {
            return null;
        }
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(id);
        return addressEntity;
    }

    default Long addressToId(AddressEntity addressEntity) {
        return addressEntity == null ? null : addressEntity.getId();
    }
}
