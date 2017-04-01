package com.sasd13.proadmin.service;

import com.sasd13.androidex.net.ICallback;
import com.sasd13.androidex.net.promise.Promise;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeacherService {

    private Promise promiseRead, promiseUpdate;
    private Map<String, String[]> parameters;

    public TeacherService() {
        promiseRead = new Promise("GET", WSResources.URL_WS_TEACHERS, Teacher.class);
        promiseUpdate = new Promise("PUT", WSResources.URL_WS_TEACHERS);
        parameters = new HashMap<>();

        promiseRead.setParameters(parameters);
    }

    public void readByNumber(ICallback callback, String number) {
        parameters.clear();
        parameters.put(EnumParameter.NUMBER.getName(), new String[]{number});
        promiseRead.registerCallback(callback);
        promiseRead.execute();
    }

    public void updateTeacher(ICallback callback, Teacher teacher, Teacher teacherToUpdate) {
        promiseUpdate.registerCallback(callback);
        promiseUpdate.execute(getUpdateWrapper(teacher, teacherToUpdate));
    }

    private TeacherUpdateWrapper getUpdateWrapper(Teacher teacher, Teacher teacherToUpdate) {
        TeacherUpdateWrapper updateWrapper = new TeacherUpdateWrapper();

        updateWrapper.setWrapped(teacher);
        updateWrapper.setNumber(teacherToUpdate.getNumber());

        return updateWrapper;
    }
}
