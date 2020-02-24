package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.persistence.AddressEntity;
import org.lock14.angularwebapp.mapper.AddressMapper;
import org.lock14.angularwebapp.persistence.AddressEntity_;
import org.lock14.angularwebapp.repository.SearchCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class AddressSpecificationGenerator implements SpecificationGenerator<AddressEntity> {
    private AddressMapper addressMapper;

    @Autowired
    public AddressSpecificationGenerator(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public Specification<AddressEntity> generate(MultiValueMap<String, String> filters) {
        return SearchCriterion.in(AddressEntity_.id,
                                  filters.getOrDefault("id", Collections.emptyList())
                                         .stream()
                                         .map(Long::valueOf)
                                         .collect(Collectors.toList()))
                              .and(SearchCriterion.in(AddressEntity_.streetAddress,
                                                      filters.get("streetAddress")))
                              .and(SearchCriterion.in(AddressEntity_.city,
                                                      filters.getOrDefault("city", Collections.emptyList())))
                              .and(SearchCriterion.in(AddressEntity_.stateEntity,
                                                      filters.getOrDefault("state", Collections.emptyList())
                                                             .stream()
                                                             .map(addressMapper::stateCodeToState)
                                                             .collect(Collectors.toList())))
                              .and(SearchCriterion.in(AddressEntity_.city,
                                                      filters.getOrDefault("zipCode", Collections.emptyList())));
    }
}
