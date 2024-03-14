package org.lock14.angularwebapp.api;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class Person {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private Long addressId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) &&
               Objects.equals(getFirstName(), person.getFirstName()) &&
               Objects.equals(getLastName(), person.getLastName()) &&
               Objects.equals(getAddressId(), person.getAddressId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddressId());
    }

    @Override
    public String toString() {
        return "Person{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", addressId=" + addressId +
               '}';
    }
}
