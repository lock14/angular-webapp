package org.lock14.angularwebapp.domain;

import org.lock14.angularwebapp.api.ApiPerson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.function.Function;

@Entity
public class Person implements ApiConvertibleEntity<Person, ApiPerson> {
    public static final String[] FIELDS = {"id", "firstName", "lastName"};

    private Long id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    public Person() {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public static Person fromApi(ApiPerson apiPerson) {
        return new Person()
                .setId(apiPerson.getId())
                .setFirstName(apiPerson.getLastName())
                .setLastName(apiPerson.getLastName());
    }

    public ApiPerson toApi() {
        return new ApiPerson()
                .setId(getId())
                .setFirstName(getFirstName())
                .setLastName(getLastName());
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
               Objects.equals(getLastName(), person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return "Person{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               '}';
    }
}
