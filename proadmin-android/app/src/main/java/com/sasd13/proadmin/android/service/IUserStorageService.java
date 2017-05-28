package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.bean.user.User;

/**
 * Created by ssaidali2 on 28/05/2017.
 */

public interface IUserStorageService {

    User read();

    void write(User user);

    void clear();
}
