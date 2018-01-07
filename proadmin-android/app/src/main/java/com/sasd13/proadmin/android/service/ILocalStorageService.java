package com.sasd13.proadmin.android.service;

import android.content.Intent;
import android.os.Parcelable;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public interface ILocalStorageService {

    Parcelable get(Intent intent, String key);

    void put(Intent intent, String key, Parcelable value);

    void remove(Intent intent, String key);
}
