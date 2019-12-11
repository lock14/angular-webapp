package org.lock14.angularwebapp.api;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ApiAddress implements Identifiable<Long> {
    private Long id;

    @NotNull
    private String streetAddress;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String zipCode;

    private Long personId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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
