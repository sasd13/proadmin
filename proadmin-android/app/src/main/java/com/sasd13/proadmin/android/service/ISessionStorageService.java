package com.sasd13.proadmin.android.service;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public interface ISessionStorageService {

    String KEY_USERID = "_USERID";
    String KEY_INTERMEDIARY = "_INTERMEDIARY";

    String get(String key);

    String getUserID();

    String getIntermediary();

    void put(String key, String value);

    void putUserID(String value);

    void putIntermediary(String value);

    void remove(String key);

    void clear();
}
