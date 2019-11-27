package org.lock14.angularwebapp.repository;

import org.lock14.angularwebapp.domain.Address;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressReposiotry extends PagingAndSortingRepository<Address, Long>,
        JpaSpecificationExecutor<Address> {
}
