package com.sasd13.proadmin.android.service.v1.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.security.HexEncoder;
import com.sasd13.proadmin.android.service.v1.IAuthenticationService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.Constants;
import com.sasd13.proadmin.itf.bean.user.log.AuthenticationResponseBean;
import com.sasd13.proadmin.util.EnumSession;
import com.sasd13.proadmin.util.Resources;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class AuthenticationService implements IAuthenticationService {

    private static final int LIMIT_IN_MILLISECONDS = 90000;

    @Override
    public ServiceResult<Map<String, String>> logIn(Map<String, String> parameters) {
        Promise promise = new Promise("POST", Resources.URL_AAA_LOGIN, AuthenticationResponseBean.class);

        Credential credential = new Credential(parameters.get(PARAMETER_USERNAME), HexEncoder.sha256(parameters.get(PARAMETER_PASSWORD)));
        AuthenticationResponseBean responseBean = (AuthenticationResponseBean) promise.execute(credential);
        Map<String, String> errors = Collections.emptyMap();
        Map<String, String> session = Collections.emptyMap();

        if (promise.isSuccess() && responseBean != null) {
            errors = responseBean.getErrors();
            session = responseBean.getData();
        }

        return new ServiceResult<>(
                errors.isEmpty() && isSessionValid(session),
                promise.getResponseCode(),
                errors,
                session
        );
    }

    private boolean isSessionValid(Map<String, String> session) {
        boolean valid = false;

        if (!session.isEmpty()) {
            try {
                DateTime now = new DateTime();
                DateTime start = new DateTime(new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).parse(session.get(EnumSession.START.getName())));

                if (start.isAfter(now.minus(LIMIT_IN_MILLISECONDS)) && start.isBefore(now.plus(LIMIT_IN_MILLISECONDS))) {
                    valid = true;
                }
            } catch (ParseException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        return valid;
    }
}
