package org.lock14.angularwebapp.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

public interface SpecificationGenerator<T> {
    Specification<T> generate(MultiValueMap<String, String> filters);
}
