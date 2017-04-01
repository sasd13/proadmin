package com.sasd13.proadmin.service.impl;

import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.service.ITeacherService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeacherService implements ITeacherService {

    private Promise promiseRead, promiseUpdate;
    private Map<String, String[]> parameters;

    @Override
    public Teacher read(String number) {
        if (promiseRead == null) {
            promiseRead = new Promise("GET", WSResources.URL_WS_TEACHERS, Teacher.class);
            parameters = new HashMap<>();

            promiseRead.setParameters(parameters);
        }

        parameters.clear();
        parameters.put(EnumParameter.NUMBER.getName(), new String[]{number});

        return ((List<Teacher>) promiseRead.execute()).get(0);
    }

    @Override
    public void update(TeacherUpdateWrapper teacherUpdateWrapper) {
        if (promiseUpdate == null) {
            promiseUpdate = new Promise("PUT", WSResources.URL_WS_TEACHERS);
        }

        promiseRead.execute(teacherUpdateWrapper);
    }
}
