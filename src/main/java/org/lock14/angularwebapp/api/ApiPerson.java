package org.lock14.angularwebapp.api;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ApiPerson {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public Long getId() {
        return id;
    }

    public ApiPerson setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ApiPerson setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ApiPerson setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiPerson)) {
            return false;
        }
        ApiPerson apiPerson = (ApiPerson) o;
        return Objects.equals(getFirstName(), apiPerson.getFirstName()) &&
               Objects.equals(getLastName(), apiPerson.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return "PersonResponse{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               '}';
    }
}
