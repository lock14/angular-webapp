package org.lock14.angularwebapp.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Person.class)
public class Person_ {
    public static volatile SingularAttribute<Person, Long> id;
    public static volatile SingularAttribute<Person, String> firstName;
    public static volatile SingularAttribute<Person, String> lastName;
}
