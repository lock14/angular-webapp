package org.lock14.angularwebapp.repository;

import org.lock14.angularwebapp.persistence.StateEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StateRepository extends PagingAndSortingRepository<StateEntity, String> {
}
