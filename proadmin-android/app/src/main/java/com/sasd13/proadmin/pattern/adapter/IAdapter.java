package com.sasd13.proadmin.pattern.adapter;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public interface IAdapter<S, T> {

    T adapt(S s);
}
