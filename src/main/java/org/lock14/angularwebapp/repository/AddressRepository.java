package org.lock14.angularwebapp.repository;

import org.lock14.angularwebapp.domain.Address;
import org.lock14.angularwebapp.domain.Person;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, Long>, JpaSpecificationExecutor<Address> {

}
