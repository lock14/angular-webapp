package org.lock14.angularwebapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public interface PagingRestService<T, ID> {
    Page<T> findAll(MultiValueMap<String, String> filters, Pageable pageable);
    Optional<T> findById(ID id);
    T save(T resource);
    void deleteById(ID id);
}
