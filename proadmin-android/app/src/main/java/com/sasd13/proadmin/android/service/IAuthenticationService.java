package com.sasd13.proadmin.android.service;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.android.bean.user.User;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IAuthenticationService {

    ServiceResult<User> logIn(Credential credential);
}
