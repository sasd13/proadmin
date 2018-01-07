package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.model.user.User;
import com.sasd13.proadmin.android.model.user.UserUpdate;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.itf.model2itf.user.UserUpdateAdapterM2I;
import com.sasd13.proadmin.android.util.adapter.itf.itf2model.user.UserAdapterI2M;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.user.UserResponseBean;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateRequestBean;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.util.EnumRestriction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class UserService implements IUserService {

    private static final String URL_AAA_USERS = AppProperties.getProperty(Names.URL_AAA_USERS);

    @Override
    public ServiceResult<User> find(String userID) {
        Promise promise = new Promise("POST", URL_AAA_USERS + "/search", UserResponseBean.class);
        SearchBean searchBean = new SearchBean();
        Map<String, Object> criterias = new HashMap<>();
        Map<String, String[]> whereCriterias = new HashMap<>();

        whereCriterias.put(EnumCriteria.USERID.getCode(), new String[]{userID});
        criterias.put(EnumRestriction.WHERE.getCode(), whereCriterias);
        searchBean.setCriterias(criterias);

        UserResponseBean responseBean = (UserResponseBean) promise.execute(searchBean);
        Map<String, String> errors = Collections.emptyMap();
        User user = null;

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                user = new UserAdapterI2M().adapt(responseBean.getData().get(0));
            }
        }

        return new ServiceResult<>(
                promise.isSuccess() && errors.isEmpty(),
                promise.getResponseCode(),
                errors,
                user
        );
    }

    @Override
    public ServiceResult<Void> update(UserUpdate userUpdate) {
        Promise promise = new Promise("POST", URL_AAA_USERS + "/update");
        UserUpdateRequestBean requestBean = new UserUpdateRequestBean();

        requestBean.setData(new UserUpdateAdapterM2I().adapt(userUpdate));
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
