package org.lock14.angularwebapp.domain;

public interface ApiConvertibleEntity<T1, T2> {
    T2 toApi();
}
