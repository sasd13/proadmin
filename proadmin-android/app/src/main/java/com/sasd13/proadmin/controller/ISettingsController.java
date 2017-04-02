package com.sasd13.proadmin.controller;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.controller.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ISettingsController extends IController {

    void readTeacher();

    void updateTeacher(Teacher teacher, Teacher teacherToUpdate);
}
