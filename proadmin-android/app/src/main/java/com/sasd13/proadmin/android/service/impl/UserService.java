package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.User;
import com.sasd13.proadmin.android.bean.update.UserUpdate;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.bean2itf.update.UserUpdateAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.UserAdapterI2B;
import com.sasd13.proadmin.itf.bean.user.UserResponseBean;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateRequestBean;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class UserService implements IUserService {

    @Override
    public ServiceResult<User> find(String userID) {
        Promise promise = new Promise("GET", Resources.URL_AAA_USERS, UserResponseBean.class);
        Map<String, String[]> parameters = new HashMap<>();

        parameters.put(EnumParameter.USERID.getName(), new String[]{userID});
        promise.setParameters(parameters);

        UserResponseBean responseBean = (UserResponseBean) promise.execute();
        User user = promise.isSuccess() && responseBean.getData() != null && !responseBean.getData().isEmpty()
                ? new UserAdapterI2B().adapt(responseBean.getData().get(0))
                : null;

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean != null ? responseBean.getErrors() : Collections.<String, String>emptyMap(),
                user
        );
    }

    @Override
    public ServiceResult<Void> update(UserUpdate userUpdate) {
        Promise promise = new Promise("PUT", Resources.URL_AAA_USERS);

        UserUpdateRequestBean requestBean = new UserUpdateRequestBean();

        requestBean.setData(new UserUpdateAdapterB2I().adapt(userUpdate));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
