package com.sasd13.proadmin.android.service;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.android.model.user.User;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface IAuthenticationService {

    boolean isAuthenticated();

    ServiceResult<User> logIn(Credential credential);

    void logOut();
}
