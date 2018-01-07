package com.sasd13.proadmin.android.service.impl;

import android.content.Intent;
import android.os.Parcelable;

import com.sasd13.proadmin.android.service.ILocalStorageService;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class LocalStorageService implements ILocalStorageService {

    @Override
    public Parcelable get(Intent intent, String key) {
        return intent.getParcelableExtra(key);
    }

    @Override
    public void put(Intent intent, String key, Parcelable value) {
        intent.putExtra(key, value);
    }

    @Override
    public void remove(Intent intent, String key) {
        intent.removeExtra(key);
    }
}
