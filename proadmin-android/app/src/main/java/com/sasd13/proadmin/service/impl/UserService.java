package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.user.User;
import com.sasd13.proadmin.service.IUserService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ws.WSResources;

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
        Promise promise = new Promise("GET", WSResources.URL_AAA_USER, User.class);

        parameters.clear();
        parameters.put(EnumParameter.USERID.getName(), new String[]{userID});
        promise.setParameters(parameters);

        List<User> results = (List<User>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                promise.isSuccess() && !results.isEmpty() ? results.get(0) : null
        );
    }
}
