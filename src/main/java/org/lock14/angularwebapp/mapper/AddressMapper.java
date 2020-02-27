package org.lock14.angularwebapp.mapper;

import org.lock14.angularwebapp.api.Address;
import org.lock14.angularwebapp.persistence.AddressEntity;
import org.lock14.angularwebapp.persistence.StateEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AddressMapper extends ApiMapper<Address, AddressEntity> {

    @Override
    AddressEntity fromApi(Address address);

    @Override
    @InheritInverseConfiguration
    Address toApi(AddressEntity addressEntity);

    default String stateToStateCode(StateEntity stateEntity) {
        return stateEntity.getCode();
    }

    default StateEntity stateCodeToState(String stateCode) {
        StateEntity stateEntity = new StateEntity();
        stateEntity.setCode(stateCode);
        return stateEntity;
    }
}
