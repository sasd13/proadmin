package com.sasd13.proadmin.service;

import android.support.annotation.StringRes;

/**
 * Created by ssaidali2 on 05/11/2016.
 */
public interface ILoginServiceCaller<T> {

    void onLoad();

    void onLogInSucceeded(T t);

    void onError(@StringRes int error);
}
