package org.lock14.angularwebapp.domain;

import org.lock14.angularwebapp.api.ApiAddress;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Address implements ApiConvertibleEntity<Address, ApiAddress> {
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

    @Column(nullable = false)
    private String state;

    @NotNull
    @Column(nullable = false)
    private Integer zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

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

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public Address setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public Address setPerson(Person person) {
        this.person = person;
        return this;
    }

    public static Address fromApi(ApiAddress apiAddress) {
        return new Address()
                .setId(apiAddress.getId())
                .setStreetAddress(apiAddress.getStreetAddress())
                .setCity(apiAddress.getCity())
                .setState(apiAddress.getState())
                .setZipCode(apiAddress.getZipCode())
                .setPerson(new Person().setId(apiAddress.getId()));
    }

    public ApiAddress toApi() {
        return new ApiAddress()
                .setId(getId())
                .setStreetAddress(getStreetAddress())
                .setCity(getCity())
                .setState(getState())
                .setZipCode(getZipCode())
                .setPersonId(getPerson().getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(getStreetAddress(), address.getStreetAddress()) &&
               Objects.equals(getCity(), address.getCity()) &&
               Objects.equals(getState(), address.getState()) &&
               Objects.equals(getZipCode(), address.getZipCode()) &&
               Objects.equals(getPerson(), address.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreetAddress(), getCity(), getState(), getZipCode(), getPerson());
    }

    @Override
    public String toString() {
        return "Address{" +
               "id=" + id +
               ", streetAddress='" + streetAddress + '\'' +
               ", city='" + city + '\'' +
               ", state='" + state + '\'' +
               ", zipCode=" + zipCode +
               ", person=" + person +
               '}';
    }
}
