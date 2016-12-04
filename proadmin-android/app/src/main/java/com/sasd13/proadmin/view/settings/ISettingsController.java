package com.sasd13.proadmin.view.settings;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.view.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ISettingsController extends IController {

    void showTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher, Teacher teacherToUpdate);
}
