package org.lock14.angularwebapp.api;

public interface Identifiable<T> {
    T getId();
    void setId(T id);
}
