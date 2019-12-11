package org.lock14.angularwebapp.api;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ApiPerson implements Identifiable<Long> {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

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
