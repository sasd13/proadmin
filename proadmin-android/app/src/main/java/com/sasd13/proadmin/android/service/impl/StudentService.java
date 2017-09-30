package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.AppProperties;
import com.sasd13.proadmin.android.util.Names;
import com.sasd13.proadmin.android.util.adapter.bean2itf.StudentAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.StudentAdapterI2B;
import com.sasd13.proadmin.itf.SearchBean;
import com.sasd13.proadmin.itf.bean.student.StudentBean;
import com.sasd13.proadmin.itf.bean.student.StudentRequestBean;
import com.sasd13.proadmin.itf.bean.student.StudentResponseBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentService implements IStudentService {

    private static final String URL_WS2_STUDENTS = AppProperties.getProperty(Names.URL_WS2_STUDENTS);

    @Override
    public ServiceResult<List<Student>> read(Map<String, Object> criterias) {
        Promise promise = new Promise("POST", URL_WS2_STUDENTS + "/search", StudentResponseBean.class);

        SearchBean searchBean = new SearchBean();
        searchBean.setCriterias(criterias);

        StudentResponseBean responseBean = (StudentResponseBean) promise.execute(searchBean);
        Map<String, String> errors = Collections.emptyMap();
        List<Student> list = new ArrayList<>();

        if (promise.isSuccess()) {
            errors = responseBean.getErrors();

            if (errors.isEmpty()) {
                StudentAdapterI2B adapter = new StudentAdapterI2B();

                for (StudentBean studentBean : responseBean.getData()) {
                    list.add(adapter.adapt(studentBean));
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
    public ServiceResult<Void> create(Student student) {
        Promise promise = new Promise("POST", URL_WS2_STUDENTS + "/create");

        StudentRequestBean requestBean = new StudentRequestBean();
        List<StudentBean> list = new ArrayList<>();

        list.add(new StudentAdapterB2I().adapt(student));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }

    @Override
    public ServiceResult<Void> update(Student student) {
        Promise promise = new Promise("POST", URL_WS2_STUDENTS + "/update");

        StudentRequestBean requestBean = new StudentRequestBean();
        List<StudentBean> list = new ArrayList<>();

        list.add(new StudentAdapterB2I().adapt(student));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
