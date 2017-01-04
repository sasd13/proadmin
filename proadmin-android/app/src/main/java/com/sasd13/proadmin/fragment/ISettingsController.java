package com.sasd13.proadmin.fragment;

import com.sasd13.proadmin.bean.member.Teacher;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ISettingsController extends IController {

    void readTeacher();

    void updateTeacher(Teacher teacher, Teacher teacherToUpdate);
}
