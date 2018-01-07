package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.model.user.User;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public interface IUserStorageService {

    String KEY_USER = "_USER";

    User read();

    void write(User user);

    void clear();
}
