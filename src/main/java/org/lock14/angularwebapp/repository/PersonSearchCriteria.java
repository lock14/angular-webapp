package org.lock14.angularwebapp.repository;

import org.lock14.angularwebapp.domain.Person;
import org.lock14.angularwebapp.domain.Person_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonSearchCriteria {
    private Set<String> firstNames;
    private Set<String> lastNames;
    private String firstNameLike;
    private String lastNameLike;

    private PersonSearchCriteria() {
    }

    public Set<String> getFirstNames() {
        return firstNames;
    }

    public Set<String> getLastNames() {
        return lastNames;
    }

    public String getFirstNameLike() {
        return firstNameLike;
    }

    public String getLastNameLike() {
        return lastNameLike;
    }

    public Specification<Person> toSpecification() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!firstNames.isEmpty()) {
                predicates.add(root.get(Person_.firstName).in(firstNames));
            }
            if (!lastNames.isEmpty()) {
                predicates.add(root.get(Person_.lastName).in(lastNames));
            }
            if (firstNameLike != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(Person_.firstName)),
                        "%" + firstNameLike.toLowerCase() + "%"));
            }
            if (lastNameLike != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(Person_.lastName)),
                        "%" + lastNameLike.toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

    public static PersonSearchCriteriaBuilder builder() {
        return new PersonSearchCriteriaBuilder();
    }

    public static final class PersonSearchCriteriaBuilder {
        private Set<String> firstNames;
        private Set<String> lastNames;
        private String firstNameLike;
        private String lastNameLike;

        private PersonSearchCriteriaBuilder() {
            firstNames = new HashSet<>();
            lastNames = new HashSet<>();
            firstNameLike = null;
            lastNameLike = null;
        }

        public PersonSearchCriteriaBuilder withFirstNames(Collection<String> firstNames) {
            safeAddAll(this.firstNames, firstNames);
            return this;
        }

        public PersonSearchCriteriaBuilder withLastNames(Collection<String> lastNames) {
            safeAddAll(this.lastNames, lastNames);
            return this;
        }

        public PersonSearchCriteriaBuilder withFirstNameLike(String firstNameLike) {
            this.firstNameLike = firstNameLike;
            return this;
        }

        public PersonSearchCriteriaBuilder withLastNameLike(String lastNameLike) {
            this.lastNameLike = lastNameLike;
            return this;
        }

        private <T> void safeAddAll(Set<T> set, Collection<T> collection) {
            if (collection != null) {
                set.addAll(collection);
            }
        }

        public PersonSearchCriteria build() {
            PersonSearchCriteria personSearchCriteria = new PersonSearchCriteria();
            personSearchCriteria.lastNameLike = this.lastNameLike;
            personSearchCriteria.lastNames = this.lastNames;
            personSearchCriteria.firstNameLike = this.firstNameLike;
            personSearchCriteria.firstNames = this.firstNames;
            return personSearchCriteria;
        }
    }
}
