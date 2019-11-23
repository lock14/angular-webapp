package org.lock14.angularwebapp.repository;

import org.lock14.angularwebapp.domain.Person;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PersonSpecification implements Specification<Person> {
    @Override
    public Specification<Person> and(Specification<Person> other) {
        return null;
    }

    @Override
    public Specification<Person> or(Specification<Person> other) {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
