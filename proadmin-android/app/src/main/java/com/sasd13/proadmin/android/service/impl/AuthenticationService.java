package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.security.HexEncoder;
import com.sasd13.proadmin.android.service.IAuthenticationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class AuthenticationService implements IAuthenticationService {

    @Override
    public ServiceResult<Map<String, String>> logIn(Map<String, String> credentials) {
        Promise promise = new Promise("POST", Resources.URL_AAA_LOGIN, Map.class);

        List<Map<String, String>> results = (List<Map<String, String>>) promise.execute(new Credential(credentials.get(PARAMETER_USERNAME), HexEncoder.sha256(credentials.get(PARAMETER_PASSWORD))));

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                promise.isSuccess() && !results.isEmpty() ? results.get(0) : Collections.<String, String>emptyMap()
        );
    }
}