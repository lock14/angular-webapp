package org.lock14.angularwebapp.domain;

import org.lock14.angularwebapp.api.ApiPerson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Person implements ApiConvertibleEntity<Person, ApiPerson> {

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
        ApiPerson apiPerson = new ApiPerson();
        apiPerson.setId(getId());
        apiPerson.setFirstName(getFirstName());
        apiPerson.setLastName(getLastName());
        return apiPerson;
    }
}
