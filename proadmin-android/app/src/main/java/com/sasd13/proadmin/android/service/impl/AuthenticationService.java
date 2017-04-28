package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.security.HexEncoder;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.itf.bean.user.log.AuthenticationResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class AuthenticationService implements IAuthenticationService {

    @Override
    public ServiceResult<Map<String, String>> logIn(Map<String, String> parameters) {
        Promise promise = new Promise("POST", Resources.URL_AAA_LOGIN, AuthenticationResponseBean.class);

        AuthenticationResponseBean responseBean = (AuthenticationResponseBean) promise.execute(new Credential(parameters.get(PARAMETER_USERNAME), HexEncoder.sha256(parameters.get(PARAMETER_PASSWORD))));

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean != null ? responseBean.getErrors() : Collections.<String, String>emptyMap(),
                promise.isSuccess() ? responseBean.getData() : Collections.<String, String>emptyMap()
        );
    }
}
