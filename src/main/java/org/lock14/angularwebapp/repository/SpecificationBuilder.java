package org.lock14.angularwebapp.repository;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class SpecificationBuilder<T1> {
    private Set<Optional<Specification<T1>>> criteria;

    public SpecificationBuilder() {
        criteria = new HashSet<>();
    }

    public <T2 extends Serializable>
    BasicFieldCriteriaBuilder<T2> with(SingularAttribute<T1, T2> field) {
        return new BasicFieldCriteriaBuilder<>(field);
    }

    public <T2 extends Comparable<? super T2> & Serializable>
    ComparableFieldCriteriaBuilder<T2> withComparable(SingularAttribute<T1, T2> field) {
        return new ComparableFieldCriteriaBuilder<>(field);
    }

    public StringFieldCriteriaBuilder withString(SingularAttribute<T1, String> field) {
        return new StringFieldCriteriaBuilder(field);
    }

    public class BasicFieldCriteriaBuilder<T2 extends Serializable> {
        protected final SingularAttribute<T1, T2> field;
        protected final Set<Specification<T1>> fieldCriteria;
        protected final DecisionBuilder decisionBuilder;

        BasicFieldCriteriaBuilder(SingularAttribute<T1, T2> field) {
            this.field = field;
            this.fieldCriteria = new HashSet<>();
            this.decisionBuilder = new DecisionBuilder();
        }

        public DecisionBuilder equalTo(T2 value) {
            if (value != null) {
                Specification<T1> spec = SearchCriterion.of(this.field, SearchCriterion.BasicOperation.EQUALS, value);
                fieldCriteria.add(spec);
            }
            return decisionBuilder;
        }

        public DecisionBuilder in(Set<T2> values) {
            if (values != null) {
                Specification<T1> spec = SearchCriterion.of(this.field, SearchCriterion.CollectionOperation.IN, values);
                fieldCriteria.add(spec);
            }
            return decisionBuilder;
        }

        public final class DecisionBuilder {

            public BasicFieldCriteriaBuilder<T2> or() {
                return BasicFieldCriteriaBuilder.this;
            }

            public <T3 extends Comparable<? super T3> & Serializable>
            BasicFieldCriteriaBuilder<T3> with(SingularAttribute<T1, T3> field) {
                Optional<Specification<T1>> spec = BasicFieldCriteriaBuilder.this.fieldCriteria.stream()
                                                                                               .reduce(Specification::and);
                SpecificationBuilder.this.criteria.add(spec);
                return new BasicFieldCriteriaBuilder<>(field);
            }

            public <T3 extends Comparable<? super T3> & Serializable>
            ComparableFieldCriteriaBuilder<T3> withComparable(SingularAttribute<T1, T3> field) {
                Optional<Specification<T1>> spec = BasicFieldCriteriaBuilder.this.fieldCriteria.stream()
                                                                                               .reduce(Specification::and);
                SpecificationBuilder.this.criteria.add(spec);
                return new ComparableFieldCriteriaBuilder<>(field);
            }

            public StringFieldCriteriaBuilder withString(SingularAttribute<T1, String> field) {
                Optional<Specification<T1>> spec = BasicFieldCriteriaBuilder.this.fieldCriteria.stream()
                                                                                               .reduce(Specification::and);
                SpecificationBuilder.this.criteria.add(spec);
                return new StringFieldCriteriaBuilder(field);
            }

            public Specification<T1> build() {
                Optional<Specification<T1>> spec = BasicFieldCriteriaBuilder.this.fieldCriteria.stream()
                                                                                               .reduce(Specification::and);
                SpecificationBuilder.this.criteria.add(spec);
                return SpecificationBuilder.this.criteria.stream()
                                                         .flatMap(Optional::stream)
                                                         .reduce(Specification::or)
                                                         .orElse(null);
            }
        }
    }

    public class ComparableFieldCriteriaBuilder<T2 extends Comparable<? super T2> & Serializable>
            extends BasicFieldCriteriaBuilder<T2> {

        ComparableFieldCriteriaBuilder(SingularAttribute<T1, T2> field) {
            super(field);
        }

        public DecisionBuilder lessThan(T2 value) {
            if (value != null) {
                Specification<T1> spec =
                        SearchCriterion.of(this.field, SearchCriterion.ComparableOperation.LESS_THAN, value);
                fieldCriteria.add(spec);
            }
            return decisionBuilder;
        }

        public DecisionBuilder lessThanOrEqualTo(T2 value) {
            if (value != null) {
                Specification<T1> spec =
                        SearchCriterion.of(this.field, SearchCriterion.ComparableOperation.LESS_THAN_OR_EQUAL_TO,
                                           value);
                fieldCriteria.add(spec);
            }
            return decisionBuilder;
        }

        public DecisionBuilder greaterThan(T2 value) {
            if (value != null) {
                Specification<T1> spec =
                        SearchCriterion.of(this.field, SearchCriterion.ComparableOperation.GREATER_THAN, value);
                fieldCriteria.add(spec);
            }
            return decisionBuilder;
        }

        public DecisionBuilder greaterThanOrEqualTo(T2 value) {
            if (value != null) {
                Specification<T1> spec =
                        SearchCriterion.of(this.field, SearchCriterion.ComparableOperation.GREATER_THAN_OR_EQUAL_TO,
                                           value);
                fieldCriteria.add(spec);
            }
            return decisionBuilder;
        }
    }

    public class StringFieldCriteriaBuilder extends ComparableFieldCriteriaBuilder<String> {

        StringFieldCriteriaBuilder(SingularAttribute<T1, String> field) {
            super(field);
        }

        public DecisionBuilder like(String value) {
            if (value != null) {
                Specification<T1> spec = SearchCriterion.of(this.field, SearchCriterion.StringOperation.LIKE, value);
                fieldCriteria.add(spec);
            }
            return decisionBuilder;
        }
    }
}
