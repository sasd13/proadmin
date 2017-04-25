package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.User;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class UserService implements IUserService {

    private Map<String, String[]> parameters;

    public UserService(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    @Override
    public ServiceResult<User> find(String userID) {
        Promise promise = new Promise("GET", Resources.URL_AAA_USERS, User.class);

        parameters.clear();
        parameters.put(EnumParameter.USERID.getName(), new String[]{userID});
        promise.setParameters(parameters);

        List<User> results = (List<User>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                promise.isSuccess() && !results.isEmpty() ? results.get(0) : null
        );
    }
}
