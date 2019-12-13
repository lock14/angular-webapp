package org.lock14.angularwebapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class State {
    @Id
    private String code;

    @Column(nullable = false)
    private String name;

    public String getCode() {
        return code;
    }

    public State setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public State setName(String name) {
        this.name = name;
        return this;
    }
}
