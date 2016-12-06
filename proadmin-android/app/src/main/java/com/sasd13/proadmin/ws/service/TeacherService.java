package com.sasd13.proadmin.ws.service;

import com.sasd13.androidex.ws.rest.service.ManageService;
import com.sasd13.androidex.ws.rest.service.ReadService;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.util.ws.WSResources;

/**
 * Created by ssaidali2 on 27/11/2016.
 */

public class TeacherService {

    public interface Caller extends ReadService.Caller<Teacher>, ManageService.Caller {
    }

    private ReadService<Teacher> readService;
    private ManageService<Teacher> manageService;

    public TeacherService(Caller caller) {
        readService = new ReadService<>(caller, WSResources.URL_WS_TEACHERS, Teacher.class);
        manageService = new ManageService<>(caller, WSResources.URL_WS_TEACHERS);
    }

    public void readByNumber(String number) {
        readService.clearParameters();
        readService.putParameters(EnumParameter.NUMBER.getName(), new String[]{number});
        readService.read();
    }

    public void updateTeacher(Teacher teacher, Teacher teacherToUpdate) {
        manageService.update(getUpdateWrapper(teacher, teacherToUpdate));
    }

    private TeacherUpdateWrapper getUpdateWrapper(Teacher teacher, Teacher teacherToUpdate) {
        TeacherUpdateWrapper updateWrapper = new TeacherUpdateWrapper();

        updateWrapper.setWrapped(teacher);
        updateWrapper.setNumber(teacherToUpdate.getNumber());

        return updateWrapper;
    }
}
