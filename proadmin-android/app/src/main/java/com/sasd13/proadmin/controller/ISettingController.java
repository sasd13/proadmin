package com.sasd13.proadmin.controller;

import com.sasd13.proadmin.bean.member.Teacher;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ISettingController extends IController {

    void actionUpdateTeacher(Teacher teacher, Teacher teacherToUpdate);
}
