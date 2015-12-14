package com.sasd13.proadmin.ws;

import java.util.List;

public interface WSConsumer<T> {

    T get(long id);

    List<T> get();

    boolean post(T t);

    void put(T t);

    void put(List<T> list);

    void delete(long id);

    void delete();
}
