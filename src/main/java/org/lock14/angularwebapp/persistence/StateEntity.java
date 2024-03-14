package org.lock14.angularwebapp.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StateEntity {
    @Id
    private String code;

    @Column(nullable = false)
    private String name;

    public String getCode() {
        return code;
    }

    public StateEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public StateEntity setName(String name) {
        this.name = name;
        return this;
    }
}
