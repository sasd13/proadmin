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

    private Promise promiseRead, promiseUpdate;

    @Override
    public ServiceResult<List<Teacher>> read(Map<String, String[]> parameters) {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_TEACHERS, Teacher.class);
        }

        promiseRead.setParameters(parameters);

        List<Teacher> results = (List<Teacher>) promiseRead.execute();

        return new ServiceResult<>(
                promiseRead.isSuccess(),
                promiseRead.getResponseCode(),
                results
        );
    }

    @Override
    public ServiceResult<Void> update(TeacherUpdateWrapper teacherUpdateWrapper) {
        if (promiseUpdate == null) {
            promiseUpdate = new Promise("PUT", WSResources.URL_WS_TEACHERS);
        }

        promiseUpdate.execute(teacherUpdateWrapper);

        return new ServiceResult<>(
                promiseUpdate.isSuccess(),
                promiseUpdate.getResponseCode(),
                null
        );
    }
}
