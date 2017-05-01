package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.service.ITeacherService;
import com.sasd13.proadmin.android.util.adapter.bean2itf.TeacherAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.TeacherAdapterI2B;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherRequestBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeacherService implements ITeacherService {

    @Override
    public ServiceResult<List<Teacher>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_TEACHERS + "/search", TeacherResponseBean.class);

        SearchBean searchBean = new SearchBean();
        searchBean.setCriterias(criterias);

        TeacherResponseBean responseBean = (TeacherResponseBean) promise.execute(searchBean);
        List<Teacher> list = new ArrayList<>();

        if (promise.isSuccess()) {
            TeacherAdapterI2B adapter = new TeacherAdapterI2B();

            for (TeacherBean teacherBean : responseBean.getData()) {
                list.add(adapter.adapt(teacherBean));
            }
        }

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean != null ? responseBean.getErrors() : Collections.<String, String>emptyMap(),
                promise.isSuccess() ? list : Collections.<Teacher>emptyList()
        );
    }

    @Override
    public ServiceResult<Void> create(Teacher teacher) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_TEACHERS + "/create");

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
        Promise promise = new Promise("POST", Resources.URL_BACKEND_TEACHERS + "/update");

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
