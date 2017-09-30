package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.service.ITeacherService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.bean2itf.TeacherAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.TeacherAdapterI2B;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherRequestBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherResponseBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeacherService implements ITeacherService {

    private static final String URL_WS2_TEACHERS = AppProperties.getProperty(Names.URL_WS2_TEACHERS);

    @Override
    public ServiceResult<List<Teacher>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", URL_WS2_TEACHERS + "/search", TeacherResponseBean.class);

        SearchBean searchBean = new SearchBean();
        searchBean.setCriterias(criterias);

        TeacherResponseBean responseBean = (TeacherResponseBean) promise.execute(searchBean);
        Map<String, String> errors = Collections.emptyMap();
        List<Teacher> list = new ArrayList<>();

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                TeacherAdapterI2B adapter = new TeacherAdapterI2B();

                for (TeacherBean teacherBean : responseBean.getData()) {
                    list.add(adapter.adapt(teacherBean));
                }
            }
        }

        return new ServiceResult<>(
                promise.isSuccess() && errors.isEmpty(),
                promise.getResponseCode(),
                errors,
                list
        );
    }

    @Override
    public ServiceResult<Void> create(Teacher teacher) {
        Promise promise = new Promise("POST", URL_WS2_TEACHERS + "/create");

        TeacherRequestBean requestBean = new TeacherRequestBean();
        List<TeacherBean> list = new ArrayList<>();

        list.add(new TeacherAdapterB2I().adapt(teacher));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> update(Teacher teacher) {
        Promise promise = new Promise("POST", URL_WS2_TEACHERS + "/update");

        TeacherRequestBean requestBean = new TeacherRequestBean();
        List<TeacherBean> list = new ArrayList<>();

        list.add(new TeacherAdapterB2I().adapt(teacher));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
