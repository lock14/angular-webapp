package org.lock14.angularwebapp.repository;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;

public final class SearchCriterion {

    private SearchCriterion() {
        // private utility class constructor
    }

    public static <T1, T2>
    Specification<T1> equal(SingularAttribute<T1, T2> field, T2 value) {
        return (root, query, builder) -> value == null ? null : builder.equal(root.get(field), value);
    }

    public static <T1, T2>
    Specification<T1> in(SingularAttribute<T1, T2> field, Collection<T2> values) {
        return (root, query, builder) -> values == null || values.isEmpty() ? null : root.get(field).in(values);
    }

    public static <T1>
    Specification<T1> in(String field, Collection<String> values) {
        return (root, query, builder) -> values == null || values.isEmpty() ? null : root.get(field).in(values);
    }

    public static <T1, T2 extends Comparable<? super T2>>
    Specification<T1> lessThan(SingularAttribute<T1, T2> field, T2 value) {
        return (root, query, builder) -> value == null ? null : builder.lessThan(root.get(field), value);
    }

    public static <T1, T2 extends Comparable<? super T2>>
    Specification<T1> lessThanOrEqualTo(SingularAttribute<T1, T2> field, T2 value) {
        return (root, query, builder) -> value == null ? null : builder.lessThanOrEqualTo(root.get(field), value);
    }

    public static <T1, T2 extends Comparable<? super T2>>
    Specification<T1> greaterThan(SingularAttribute<T1, T2> field, T2 value) {
        return (root, query, builder) -> value == null ? null : builder.greaterThan(root.get(field), value);
    }

    public static <T1, T2 extends Comparable<? super T2>>
    Specification<T1> greaterThanOrEqualTo(SingularAttribute<T1, T2> field, T2 value) {
        return (root, query, builder) -> value == null ? null : builder.greaterThanOrEqualTo(root.get(field), value);
    }

    public static <T1>
    Specification<T1> like(SingularAttribute<T1, String> field, String value) {
        return (root, query, builder) -> value == null ? null : builder.like(root.get(field), value);
    }

    public static <T1>
    Specification<T1> likeIgnoreCase(SingularAttribute<T1, String> field, String value) {
        return (root, query, builder) -> value == null ? null : builder.like(builder.lower(root.get(field)), value.toLowerCase());
    }
}
