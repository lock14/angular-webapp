package org.lock14.angularwebapp.repository;

import org.lock14.angularwebapp.persistence.StateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StateRepository extends CrudRepository<StateEntity, String>, PagingAndSortingRepository<StateEntity, String> {
}
