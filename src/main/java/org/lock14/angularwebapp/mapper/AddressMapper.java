package org.lock14.angularwebapp.mapper;

import org.lock14.angularwebapp.api.ApiAddress;
import org.lock14.angularwebapp.domain.Address;
import org.lock14.angularwebapp.domain.State;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper extends ApiMapper<ApiAddress, Address> {

    @Override
    Address fromApi(ApiAddress apiAddress);

    @Override
    @InheritInverseConfiguration
    ApiAddress toApi(Address address);

    default String stateToStateCode(State state) {
        return state.getCode();
    }

    default State stateCodeToState(String stateCode) {
        State state = new State();
        state.setCode(stateCode);
        return state;
    }
}
