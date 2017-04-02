package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.service.IAuthenticationService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class AuthenticationService implements IAuthenticationService {

    private Promise promiseLogin;

    @Override
    public ServiceResult<Map<String, String>> logIn(Map<String, String> credentials) {
        if (promiseLogin == null) {
            promiseLogin = new Promise("POST", WSResources.URL_AAA_LOGIN, Map.class);
        }

        List<Map<String, String>> results = (List<Map<String, String>>) promiseLogin.execute(new Credential(credentials.get(PARAMETER_USERNAME), credentials.get(PARAMETER_PASSWORD)));

        return new ServiceResult<>(
                promiseLogin.isSuccess(),
                promiseLogin.getResponseCode(),
                !results.isEmpty() ? results.get(0) : Collections.<String, String>emptyMap()
        );
    }
}
