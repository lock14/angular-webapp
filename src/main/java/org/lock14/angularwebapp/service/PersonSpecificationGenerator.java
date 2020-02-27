package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.mapper.AddressMapper;
import org.lock14.angularwebapp.persistence.AddressEntity;
import org.lock14.angularwebapp.persistence.AddressEntity_;
import org.lock14.angularwebapp.persistence.PersonEntity;
import org.lock14.angularwebapp.mapper.PersonMapper;
import org.lock14.angularwebapp.persistence.PersonEntity_;
import org.lock14.angularwebapp.repository.SearchCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class PersonSpecificationGenerator implements SpecificationGenerator<PersonEntity> {
    private final PersonMapper personMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public PersonSpecificationGenerator(PersonMapper personMapper,
                                        AddressMapper addressMapper) {
        this.personMapper = personMapper;
        this.addressMapper = addressMapper;
    }

    public Specification<PersonEntity> generate(MultiValueMap<String, String> filters) {
        return SearchCriterion.in(PersonEntity_.id,
                                  filters.getOrDefault("id", Collections.emptyList())
                                         .stream()
                                         .map(Long::valueOf)
                                         .collect(Collectors.toList()))
                              .and(SearchCriterion.in(PersonEntity_.address,
                                                      filters.getOrDefault("addressId", Collections.emptyList())
                                                             .stream()
                                                             .map(Long::valueOf)
                                                             .map(personMapper::idToAddress)
                                                             .collect(Collectors.toList())))
                              .and(SearchCriterion.in(PersonEntity_.firstName,
                                                      filters.getOrDefault("firstName", Collections.emptyList())))
                              .and(SearchCriterion.in(PersonEntity_.lastName,
                                                      filters.getOrDefault("lastName", Collections.emptyList())))
                              .and(SearchCriterion.joinIn(PersonEntity_.address, AddressEntity_.city,
                                                          filters.getOrDefault("city", Collections.emptyList())))
                              .and(SearchCriterion.joinIn(PersonEntity_.address, AddressEntity_.state,
                                                          filters.getOrDefault("state", Collections.emptyList())
                                                                 .stream()
                                                                 .map(addressMapper::stateCodeToState)
                                                                 .collect(Collectors.toList())))
                              .and(SearchCriterion.joinIn(PersonEntity_.address, AddressEntity_.zipCode,
                                                          filters.getOrDefault("zipCode", Collections.emptyList())));
    }
}
