package com.sasd13.proadmin.service;

import android.support.annotation.StringRes;

/**
 * Created by ssaidali2 on 05/11/2016.
 */

public interface IManageServiceCaller<T> {

    void onLoad();

    void onCreateSucceeded(T t);

    void onUpdateSucceeded(T t);

    void onDeleteSucceeded();

    void onError(@StringRes int error);
}
