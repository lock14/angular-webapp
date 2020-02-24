package org.lock14.angularwebapp.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
