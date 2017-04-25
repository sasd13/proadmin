package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.bean.user.User;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IUserService {

    ServiceResult<User> find(String userID);
}
