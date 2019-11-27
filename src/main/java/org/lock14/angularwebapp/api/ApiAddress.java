package org.lock14.angularwebapp.api;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ApiAddress {
    private Long id;

    @NotNull
    private String streetAddress;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private Integer zipCode;

    private Long personId;

    public Long getId() {
        return id;
    }

    public ApiAddress setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public ApiAddress setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ApiAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public ApiAddress setState(String state) {
        this.state = state;
        return this;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public ApiAddress setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public Long getPersonId() {
        return personId;
    }

    public ApiAddress setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiAddress)) {
            return false;
        }
        ApiAddress that = (ApiAddress) o;
        return Objects.equals(getStreetAddress(), that.getStreetAddress()) &&
               Objects.equals(getCity(), that.getCity()) &&
               Objects.equals(getState(), that.getState()) &&
               Objects.equals(getZipCode(), that.getZipCode()) &&
               Objects.equals(getPersonId(), that.getPersonId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreetAddress(), getCity(), getState(), getZipCode(), getPersonId());
    }

    @Override
    public String toString() {
        return "ApiAddress{" +
               "id=" + id +
               ", streetAddress='" + streetAddress + '\'' +
               ", city='" + city + '\'' +
               ", state='" + state + '\'' +
               ", zipCode=" + zipCode +
               ", personId=" + personId +
               '}';
    }
}
