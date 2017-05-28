package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.ISessionStorageService;
import com.sasd13.proadmin.android.service.IUserStorageService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.itf2bean.user.UserAdapterI2B;
import com.sasd13.proadmin.itf.bean.user.log.AuthenticationResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class AuthenticationService implements IAuthenticationService {

    private ISessionStorageService sessionStorageService;
    private IUserStorageService userStorageService;

    public AuthenticationService(ISessionStorageService sessionStorageService, IUserStorageService userStorageService) {
        this.sessionStorageService = sessionStorageService;
        this.userStorageService = userStorageService;
    }

    @Override
    public boolean isAuthenticated() {
        return sessionStorageService.getUserID() != null && sessionStorageService.getIntermediary() != null;
    }

    @Override
    public ServiceResult<User> logIn(Credential credential) {
        Promise promise = new Promise("POST", Resources.URL_AAA_LOGIN, AuthenticationResponseBean.class);

        AuthenticationResponseBean responseBean = (AuthenticationResponseBean) promise.execute(credential);
        boolean authenticated = false;
        Map<String, String> errors = Collections.emptyMap();
        User user = null;

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                authenticated = true;
                user = new UserAdapterI2B().adapt(responseBean.getUser());
            }
        }

        return new ServiceResult<>(
                authenticated,
                promise.getResponseCode(),
                errors,
                user
        );
    }

    @Override
    public ServiceResult<Void> logOut() {
        sessionStorageService.clear();
        userStorageService.clear();

        return new ServiceResult<>(
                true,
                200
        );
    }
}
