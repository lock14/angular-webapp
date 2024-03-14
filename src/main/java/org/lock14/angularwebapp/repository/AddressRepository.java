package org.lock14.angularwebapp.repository;

import org.lock14.angularwebapp.persistence.AddressEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long>, PagingAndSortingRepository<AddressEntity, Long>, JpaSpecificationExecutor<AddressEntity> {

}
