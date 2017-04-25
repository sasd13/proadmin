package com.sasd13.proadmin.android.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.WSResources;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdate;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeacherService implements ITeacherService {

    @Override
    public ServiceResult<List<Teacher>> read(Map<String, String[]> parameters) {
        Promise promise = new Promise("GET", WSResources.URL_WS_TEACHERS, Teacher.class);

        promise.setParameters(parameters);

        List<Teacher> results = (List<Teacher>) promise.execute();

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                results
        );
    }

    @Override
    public ServiceResult<Void> update(TeacherUpdate teacherUpdate) {
        Promise promise = new Promise("PUT", WSResources.URL_WS_TEACHERS);

        promise.execute(new TeacherUpdate[]{teacherUpdate});

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                promise.getResponseHeaders(),
                null
        );
    }
}
