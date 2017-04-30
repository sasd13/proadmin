package com.sasd13.proadmin.android.service.v2.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.service.v2.IStudentService;
import com.sasd13.proadmin.android.util.adapter.bean2itf.v2.StudentAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.v2.StudentAdapterI2B;
import com.sasd13.proadmin.itf.bean.student.StudentBean;
import com.sasd13.proadmin.itf.bean.student.StudentRequestBean;
import com.sasd13.proadmin.itf.bean.student.StudentResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentService implements IStudentService {

    @Override
    public ServiceResult<List<Student>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", Resources.URL_BACKEND_STUDENTS, StudentResponseBean.class);

        promise.setParameters(parameters);

        StudentResponseBean responseBean = (StudentResponseBean) promise.execute();
        List<Student> list = new ArrayList<>();

        if (promise.isSuccess()) {
            StudentAdapterI2B adapter = new StudentAdapterI2B();

            for (StudentBean studentBean : responseBean.getData()) {
                list.add(adapter.adapt(studentBean));
            }
        }

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean != null ? responseBean.getErrors() : Collections.<String, String>emptyMap(),
                promise.isSuccess() ? list : Collections.<Student>emptyList()
        );
    }

    @Override
    public ServiceResult<Void> create(Student student) {
        Promise promise = new Promise("POST", Resources.URL_BACKEND_STUDENTS);

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
        Promise promise = new Promise("PUT", Resources.URL_BACKEND_STUDENTS);

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
