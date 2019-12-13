package org.lock14.angularwebapp.domain;

import org.lock14.angularwebapp.api.ApiAddress;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Address implements ApiConvertibleEntity<ApiAddress> {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String streetAddress;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private State state;

    @NotNull
    @Column(nullable = false)
    private String zipCode;

    public Long getId() {
        return id;
    }

    public Address setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public Address setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public State getState() {
        return state;
    }

    public Address setState(State state) {
        this.state = state;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Address setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public static Address fromApi(ApiAddress apiAddress) {
        return new Address()
                .setId(apiAddress.getId())
                .setStreetAddress(apiAddress.getStreetAddress())
                .setCity(apiAddress.getCity())
                .setState(new State().setCode(apiAddress.getState()))
                .setZipCode(apiAddress.getZipCode());
    }

    public ApiAddress toApi() {
        ApiAddress apiAddress = new ApiAddress();
        apiAddress.setId(getId());
        apiAddress.setStreetAddress(getStreetAddress());
        apiAddress.setCity(getCity());
        apiAddress.setState(getState().getCode());
        apiAddress.setZipCode(getZipCode());
        return apiAddress;
    }
}
