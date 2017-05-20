package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.bean.user.UserUpdate;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IUserService {

    ServiceResult<User> find(String userID);

    ServiceResult<Void> update(UserUpdate userUpdate);
}
