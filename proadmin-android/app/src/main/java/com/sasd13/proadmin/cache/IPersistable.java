package com.sasd13.proadmin.cache;

/**
 * Created by Samir on 23/01/2016.
 */
public interface IPersistable<T> {

    void persist(T t);
}
