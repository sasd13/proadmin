package com.sasd13.proadmin.service;

import com.sasd13.androidex.ws.rest.promise.ManagePromise;
import com.sasd13.androidex.ws.rest.promise.ReadPromise;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeacherService {

    public interface Callback extends ReadPromise.Callback<Teacher>, ManagePromise.Callback {
    }

    private ReadPromise<Teacher> readPromise;
    private ManagePromise<Teacher> managePromise;

    public TeacherService(Callback callback) {
        readPromise = new ReadPromise<>(callback, WSResources.URL_WS_TEACHERS, Teacher.class);
        managePromise = new ManagePromise<>(callback, WSResources.URL_WS_TEACHERS);
    }

    public void readByNumber(String number) {
        readPromise.clearParameters();
        readPromise.putParameters(EnumParameter.NUMBER.getName(), new String[]{number});
        readPromise.read();
    }

    public void updateTeacher(Teacher teacher, Teacher teacherToUpdate) {
        managePromise.update(getUpdateWrapper(teacher, teacherToUpdate));
    }

    private TeacherUpdateWrapper getUpdateWrapper(Teacher teacher, Teacher teacherToUpdate) {
        TeacherUpdateWrapper updateWrapper = new TeacherUpdateWrapper();

        updateWrapper.setWrapped(teacher);
        updateWrapper.setNumber(teacherToUpdate.getNumber());

        return updateWrapper;
    }
}
