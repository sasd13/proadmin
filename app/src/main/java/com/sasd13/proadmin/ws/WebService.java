package com.sasd13.proadmin.ws;

import java.util.List;

public interface WebService<T> {

    T get(int id);

    List<T> get();

    boolean post(T t);

    void put(T t);

    void put(List<T> list);

    void delete(int id);

    void delete();
}
