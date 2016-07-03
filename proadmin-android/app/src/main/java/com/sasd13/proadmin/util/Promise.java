package com.sasd13.proadmin.util;

/**
 * Created by Samir on 16/01/2016.
 */
public interface Promise {

    void onLoad();

    void onSuccess();

    void onFail();
}
