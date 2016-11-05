package com.sasd13.proadmin.service;

import android.support.annotation.StringRes;

/**
 * Created by ssaidali2 on 05/11/2016.
 */

public interface IReadServiceCaller<T> {

    void onLoad();

    void onReadSucceeded(T t);

    void onError(@StringRes int error);
}
