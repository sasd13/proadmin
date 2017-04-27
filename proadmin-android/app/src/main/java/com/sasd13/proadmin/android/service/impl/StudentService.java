package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.bean.update.StudentUpdate;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.android.util.adapter.bean2itf.StudentAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.bean2itf.update.StudentUpdateAdapterB2I;
import com.sasd13.proadmin.android.util.adapter.itf2bean.StudentAdapterI2B;
import com.sasd13.proadmin.itf.bean.student.StudentBean;
import com.sasd13.proadmin.itf.bean.student.StudentRequestBean;
import com.sasd13.proadmin.itf.bean.student.StudentResponseBean;
import com.sasd13.proadmin.util.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentService implements IStudentService {

    @Override
    public ServiceResult<List<Student>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", Resources.URL_WS_STUDENTS, StudentResponseBean.class);

        promise.setParameters(parameters);

        StudentResponseBean responseBean = (StudentResponseBean) promise.execute();
        List<Student> list = new ArrayList<>();
        StudentAdapterI2B adapter = new StudentAdapterI2B();

        for (StudentBean studentBean : responseBean.getData()) {
            list.add(adapter.adapt(studentBean));
        }

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                responseBean.getErrors(),
                list
        );
    }

    @Override
    public ServiceResult<Void> create(Student student) {
        Promise promise = new Promise("POST", Resources.URL_WS_STUDENTS);

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
    public ServiceResult<Void> update(StudentUpdate studentUpdate) {
        Promise promise = new Promise("PUT", Resources.URL_WS_STUDENTS);

        StudentRequestBean requestBean = new StudentRequestBean();
        List<StudentBean> list = new ArrayList<>();

        list.add(new StudentUpdateAdapterB2I().adapt(studentUpdate));
        requestBean.setData(list);
        promise.execute(requestBean);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode()
        );
    }
}
