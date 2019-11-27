package org.lock14.angularwebapp.service;

import org.lock14.angularwebapp.api.ApiAddress;
import org.lock14.angularwebapp.domain.Address;
import org.lock14.angularwebapp.repository.AddressReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private AddressReposiotry addressReposiotry;

    @Autowired
    public AddressService(AddressReposiotry addressReposiotry) {
        this.addressReposiotry = addressReposiotry;
    }

    public Page<ApiAddress> findAll(Pageable pageable) {
        return addressReposiotry.findAll(pageable)
                                .map(Address::toApi);
    }

    public Optional<ApiAddress> findById(Long id) {
        return addressReposiotry.findById(id)
                                .map(Address::toApi);
    }

    public ApiAddress save(ApiAddress address) {
        return addressReposiotry.save(Address.fromApi(address))
                                .toApi();
    }

    public void deleteById(Long id) {
        addressReposiotry.deleteById(id);
    }
}
