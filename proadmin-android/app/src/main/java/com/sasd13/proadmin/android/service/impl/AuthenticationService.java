package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.security.HexEncoder;
import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.ISessionStorageService;
import com.sasd13.proadmin.android.service.IUserStorageService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.itf2bean.user.UserAdapterI2B;
import com.sasd13.proadmin.itf.bean.user.log.AuthenticationResponseBean;

import java.util.Collections;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class AuthenticationService implements IAuthenticationService {

    private static final String URL_AAA_LOGIN = AppProperties.getProperty(Names.URL_AAA_LOGIN);

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
        Promise promise = new Promise("POST", URL_AAA_LOGIN, AuthenticationResponseBean.class);

        AuthenticationResponseBean responseBean = (AuthenticationResponseBean) promise.execute(new Credential(credential.getUsername(), HexEncoder.sha256(credential.getPassword())));
        boolean authenticated = false;
        Map<String, String> errors = Collections.emptyMap();
        User user = null;

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                authenticated = true;
                user = new UserAdapterI2B().adapt(responseBean.getUser());

                sessionStorageService.putUserID(user.getUserID());
                sessionStorageService.putIntermediary(user.getIntermediary());
                userStorageService.write(user);
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
    public void logOut() {
        sessionStorageService.clear();
        userStorageService.clear();
    }
}
