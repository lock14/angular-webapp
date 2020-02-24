package org.lock14.angularwebapp.resource;

import org.lock14.angularwebapp.api.Address;
import org.lock14.angularwebapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.lock14.angularwebapp.AppConstants.ADDRESSES_URI;

@RestController
@RequestMapping(ADDRESSES_URI)
public class AddressController extends PagingRestController<Address, Long> {

    @Autowired
    public AddressController(AddressService addressService) {
        super(addressService);
    }
}
