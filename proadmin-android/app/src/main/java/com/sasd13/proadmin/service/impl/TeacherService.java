package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.service.ServiceResult;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

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
                results
        );
    }

    @Override
    public ServiceResult<Void> update(TeacherUpdateWrapper teacherUpdateWrapper) {
        Promise promise = new Promise("PUT", WSResources.URL_WS_TEACHERS);

        promise.execute(teacherUpdateWrapper);

        return new ServiceResult<>(
                promise.isSuccess(),
                promise.getResponseCode(),
                null
        );
    }
}
