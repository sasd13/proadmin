package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.bean.update.StudentUpdate;
import com.sasd13.proadmin.android.service.IStudentService;
import com.sasd13.proadmin.android.service.ServiceResult;
import com.sasd13.proadmin.util.Resources;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class StudentService implements IStudentService {

    @Override
    public ServiceResult<List<Student>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", Resources.URL_WS_STUDENTS, Student.class);

        promise.setParameters(parameters);

        List<Student> results = (List<Student>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                results
        );
    }

    @Override
    public ServiceResult<Void> create(Student student) {
        Promise promise = new Promise("POST", Resources.URL_WS_STUDENTS);

        promise.execute(new Student[]{student});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }

    @Override
    public ServiceResult<Void> update(StudentUpdate studentUpdate) {
        Promise promise = new Promise("PUT", Resources.URL_WS_STUDENTS);

        promise.execute(new StudentUpdate[]{studentUpdate});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                Collections.<String, String>emptyMap(),
                null
        );
    }
}
