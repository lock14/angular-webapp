package org.lock14.angularwebapp.mapper;

public interface ApiMapper<T1, T2> {

    T2 fromApi(T1 t1);

    T1 toApi(T2 t2);
}
