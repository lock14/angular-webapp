package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.persistence.AddressEntity;
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
    private final AddressSpecificationGenerator addressSpecificationGenerator;
    private final PersonMapper personMapper;

    @Autowired
    public PersonSpecificationGenerator(PersonMapper personMapper,
                                        AddressSpecificationGenerator addressSpecificationGenerator) {
        this.personMapper = personMapper;
        this.addressSpecificationGenerator = addressSpecificationGenerator;
    }

    public Specification<PersonEntity> generate(MultiValueMap<String, String> filters) {
        Specification<PersonEntity> spec = (root, query, builder) -> {
            Join<PersonEntity, AddressEntity> join = root.join(PersonEntity_.addressEntity, JoinType.INNER);
            return builder.and(join.get(PersonEntity_.ID).in(Collections.emptyList()),
                               join.get(PersonEntity_.ID).in(Collections.emptyList()));
        };
        return SearchCriterion.in(PersonEntity_.id,
                                  filters.getOrDefault("id", Collections.emptyList())
                                         .stream()
                                         .map(Long::valueOf)
                                         .collect(Collectors.toList()))
                              .and(SearchCriterion.in(PersonEntity_.addressEntity,
                                                      filters.getOrDefault("addressId", Collections.emptyList())
                                                             .stream()
                                                             .map(Long::valueOf)
                                                             .map(personMapper::idToAddress)
                                                             .collect(Collectors.toList())))
                              .and(SearchCriterion.in(PersonEntity_.firstName,
                                                      filters.getOrDefault("firstName", Collections.emptyList())))
                              .and(SearchCriterion.in(PersonEntity_.lastName,
                                                      filters.getOrDefault("lastName", Collections.emptyList())));
    }
}
