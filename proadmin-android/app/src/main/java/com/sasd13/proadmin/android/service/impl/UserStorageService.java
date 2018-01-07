package com.sasd13.proadmin.android.service.impl;

import android.app.Activity;
import android.content.Intent;

import com.sasd13.proadmin.android.model.user.User;
import com.sasd13.proadmin.android.service.ILocalStorageService;
import com.sasd13.proadmin.android.service.IUserStorageService;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class UserStorageService implements IUserStorageService {

    private static final String KEY_USER = "_USER";

    private ILocalStorageService localStorageService;

    public UserStorageService(ILocalStorageService localStorageService) {
        this.localStorageService = localStorageService;
    }

    @Override
    public User read(Intent intent) {
        return (User) localStorageService.get(intent, KEY_USER);
    }

    @Override
    public User read(Activity activity) {
        return read(activity.getIntent());
    }

    @Override
    public void write(Intent intent, User user) {
        localStorageService.put(intent, KEY_USER, user);
    }

    @Override
    public void write(Activity activity, User user) {
        write(activity.getIntent(), user);
    }

    @Override
    public void clear(Intent intent) {
        localStorageService.remove(intent, KEY_USER);
    }

    @Override
    public void clear(Activity activity) {
        clear(activity.getIntent());
    }
}
