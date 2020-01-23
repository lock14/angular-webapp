package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.domain.Address;
import org.lock14.angularwebapp.domain.Address_;
import org.lock14.angularwebapp.mapper.AddressMapper;
import org.lock14.angularwebapp.repository.SearchCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class AddressSpecificationGenerator implements SpecificationGenerator<Address> {
    private AddressMapper addressMapper;

    @Autowired
    public AddressSpecificationGenerator(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public Specification<Address> generate(MultiValueMap<String, String> filters) {
        return SearchCriterion.in(Address_.id,
                                  filters.getOrDefault("id", Collections.emptyList())
                                         .stream()
                                         .map(Long::valueOf)
                                         .collect(Collectors.toList()))
                              .and(SearchCriterion.in(Address_.streetAddress,
                                                      filters.get("streetAddress")))
                              .and(SearchCriterion.in(Address_.city,
                                                      filters.getOrDefault("city", Collections.emptyList())))
                              .and(SearchCriterion.in(Address_.state,
                                                      filters.getOrDefault("state", Collections.emptyList())
                                                             .stream()
                                                             .map(addressMapper::stateCodeToState)
                                                             .collect(Collectors.toList())))
                              .and(SearchCriterion.in(Address_.city,
                                                      filters.getOrDefault("zipCode", Collections.emptyList())));
    }
}
