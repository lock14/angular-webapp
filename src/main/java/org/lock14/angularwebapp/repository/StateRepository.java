package org.lock14.angularwebapp.repository;

import org.lock14.angularwebapp.domain.State;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StateRepository extends PagingAndSortingRepository<State, String> {
}
