package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.util.LocalStorage;
import com.sasd13.proadmin.android.model.user.User;
import com.sasd13.proadmin.android.service.IUserStorageService;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public class UserStorageService implements IUserStorageService {

    private LocalStorage localStorage;

    public UserStorageService(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    @Override
    public User read() {
        return (User) localStorage.get(KEY_USER);
    }

    @Override
    public void write(User user) {
        localStorage.put(KEY_USER, user);
    }

    @Override
    public void clear() {
        localStorage.remove(KEY_USER);
    }
}
