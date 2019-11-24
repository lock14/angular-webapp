package org.lock14.angularwebapp.repository;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;
import java.util.Objects;

public abstract class SearchCriterion<T1, T2> implements Specification<T1> {
    private static final String SEARCH_REGEX = "(\\w+)(<|>|<=|>=|~=|=|#)(\\w+|\\[(\\w+,)*(\\w+)?\\]);";
    private final SingularAttribute<T1, T2> field;
    private final Operation operation;
    private final Object value;

    public interface Operation {
    }

    public enum BasicOperation implements Operation {
        EQUALS("=");

        private String value;

        BasicOperation(String value) {
            this.value = value;
        }

        public static BasicOperation fromString(String value) {
            for (BasicOperation operation : BasicOperation.values()) {
                if (Objects.equals(value.toLowerCase(), operation.value)) {
                    return operation;
                }
            }
            throw new IllegalArgumentException("Invalid operation:" + value);
        }
    }

    public enum CollectionOperation implements Operation {
        IN("#");

        private String value;

        CollectionOperation(String value) {
            this.value = value;
        }

        public static CollectionOperation fromString(String value) {
            for (CollectionOperation operation : CollectionOperation.values()) {
                if (Objects.equals(value.toLowerCase(), operation.value)) {
                    return operation;
                }
            }
            throw new IllegalArgumentException("Invalid operation:" + value);
        }
    }

    public enum ComparableOperation implements Operation {
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL_TO("<="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL_TO(">=");

        private String value;

        ComparableOperation(String value) {
            this.value = value;
        }

        public static ComparableOperation fromString(String value) {
            for (ComparableOperation operation : ComparableOperation.values()) {
                if (Objects.equals(value.toLowerCase(), operation.value)) {
                    return operation;
                }
            }
            throw new IllegalArgumentException("Invalid operation:" + value);
        }
    }

    public enum StringOperation implements Operation {
        LIKE("~=");

        private String value;

        StringOperation(String value) {
            this.value = value;
        }

        public static StringOperation fromString(String value) {
            for (StringOperation operation : StringOperation.values()) {
                if (Objects.equals(value.toLowerCase(), operation.value)) {
                    return operation;
                }
            }
            throw new IllegalArgumentException("Invalid operation:" + value);
        }
    }

    private SearchCriterion(SingularAttribute<T1, T2> field, Operation operation, Object value) {
        this.field = Objects.requireNonNull(field, "key cannot bey null");
        this.operation = Objects.requireNonNull(operation, "operation cannot bey null");
        this.value = value;
    }

    public static <T1, T2> Specification<T1> parse(String criteria) {
        return null;
    }

    public static <T1, T2> Specification<T1> equalTo(SingularAttribute<T1, T2> field, T2 value) {
        return of(field, BasicOperation.EQUALS, value);
    }

    public static <T1, T2>
    Specification<T1> of(SingularAttribute<T1, T2> field, BasicOperation operation, T2 value) {
        return new BasicCriterion<>(field, operation, value);
    }

    public static <T1, T2> Specification<T1> in(SingularAttribute<T1, T2> field, Collection<T2> values) {
        return of(field, CollectionOperation.IN, values);
    }

    public static <T1, T2>
    Specification<T1> of(SingularAttribute<T1, T2> field, CollectionOperation operation, Collection<T2> values) {
        return new CollectionCriterion<>(field, operation, values);
    }

    public static <T1, T2 extends Comparable<? super T2>>
    Specification<T1> lessThan(SingularAttribute<T1, T2> field, T2 value) {
        return of(field, ComparableOperation.LESS_THAN, value);
    }

    public static <T1, T2 extends Comparable<? super T2>>
    Specification<T1> lessThanOrEqualTo(SingularAttribute<T1, T2> field, T2 value) {
        return of(field, ComparableOperation.LESS_THAN_OR_EQUAL_TO, value);
    }

    public static <T1, T2 extends Comparable<? super T2>>
    Specification<T1> greaterThan(SingularAttribute<T1, T2> field, T2 value) {
        return of(field, ComparableOperation.GREATER_THAN, value);
    }

    public static <T1, T2 extends Comparable<? super T2>>
    Specification<T1> greaterThanOrEqualTo(SingularAttribute<T1, T2> field, T2 value) {
        return of(field, ComparableOperation.GREATER_THAN_OR_EQUAL_TO, value);
    }

    public static <T1, T2 extends Comparable<? super T2>>
    Specification<T1> of(SingularAttribute<T1, T2> field, ComparableOperation operation, T2 value) {
        return new ComparableCriterion<>(field, operation, value);
    }

    public static <T1> Specification<T1> like(SingularAttribute<T1, String> field, String value) {
        return of(field, StringOperation.LIKE, value);
    }

    public static <T1> Specification<T1>
    of(SingularAttribute<T1, String> field, StringOperation operation, String value) {
        return new StringCriterion<>(field, operation, value);
    }

    public final SingularAttribute<T1, T2> getField() {
        return field;
    }

    public final Operation getOperation() {
        return operation;
    }

    public final Object getValue() {
        return value;
    }

    @Override
    public abstract Predicate toPredicate(Root<T1> root, CriteriaQuery<?> query, CriteriaBuilder builder);

    private static final class BasicCriterion<T1, T2> extends SearchCriterion<T1, T2> {

        private BasicOperation operation;

        private BasicCriterion(SingularAttribute<T1, T2> field,
                       BasicOperation operation, T2 value) {
            super(field, operation, value);
            this.operation = operation;
        }

        @Override
        public Predicate toPredicate(Root<T1> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
            if (operation == BasicOperation.EQUALS && getValue() != null) {
                return builder.equal(root.get(getField()), getValue());
            }
            return null;
        }
    }

    private static final class StringCriterion<T1> extends SearchCriterion<T1, String> {

        private StringOperation operation;

        private StringCriterion(SingularAttribute<T1, String> field,
                                StringOperation operation, String value) {
            super(field, operation, value);
            this.operation = operation;
        }

        @Override
        public Predicate toPredicate(Root<T1> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
            if (operation == StringOperation.LIKE && getValue() != null) {
                return builder.like(root.get(getField()), "%" + getValue() + "%");
            }
            return null;
        }
    }

    private static final class CollectionCriterion<T1, T2> extends SearchCriterion<T1, T2> {
        private final CollectionOperation operation;

        private CollectionCriterion(SingularAttribute<T1, T2> field,
                                    CollectionOperation operation, Collection<T2> values) {
            super(field, operation, values);
            this.operation = operation;
        }

        @Override
        public Predicate toPredicate(Root<T1> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
            if (operation == CollectionOperation.IN && getValue() != null) {
                return root.get(getField()).in(getValue());
            }
            return null;
        }
    }

    private static final class ComparableCriterion<T1, T2 extends Comparable<? super T2>>
            extends SearchCriterion<T1, T2> {
        private ComparableOperation operation;

        private ComparableCriterion(SingularAttribute<T1, T2> field,
                                    ComparableOperation operation, T2 value) {
            super(field, operation, value);
            this.operation = operation;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Predicate toPredicate(Root<T1> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
            if (getValue() != null) {
                switch (operation) {
                    case LESS_THAN:
                        return builder.lessThan(root.get(getField()), (T2) getValue());
                    case LESS_THAN_OR_EQUAL_TO:
                        return builder.lessThanOrEqualTo(root.get(getField()), (T2) getValue());
                    case GREATER_THAN:
                        return builder.greaterThan(root.get(getField()), (T2) getValue());
                    case GREATER_THAN_OR_EQUAL_TO:
                        return builder.greaterThanOrEqualTo(root.get(getField()), (T2) getValue());
                    default:
                        return null;
                }
            }
            return null;
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SearchCriterion)) {
            return false;
        }
        SearchCriterion<?, ?> that = (SearchCriterion<?, ?>) o;
        return Objects.equals(field, that.field) &&
               operation == that.operation &&
               Objects.equals(value, that.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(field, operation, value);
    }

    @Override
    public String toString() {
        return "SearchCriterion{" +
               "field='" + field + '\'' +
               ", operation=" + operation +
               ", value=" + value +
               '}';
    }
}
