package com.sasd13.proadmin.service;

import android.support.annotation.StringRes;

import java.util.List;

/**
 * Created by ssaidali2 on 05/11/2016.
 */

public interface IReadServiceCaller<T> {

    void onLoad();

    void onReadSucceeded(List<T> ts);

    void onError(@StringRes int error);
}
