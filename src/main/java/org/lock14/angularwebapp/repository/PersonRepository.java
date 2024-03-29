package org.lock14.angularwebapp.repository;

import org.lock14.angularwebapp.persistence.PersonEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long>, PagingAndSortingRepository<PersonEntity, Long>, JpaSpecificationExecutor<PersonEntity> {

}
