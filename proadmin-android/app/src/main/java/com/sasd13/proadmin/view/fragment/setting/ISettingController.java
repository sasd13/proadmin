package com.sasd13.proadmin.view.fragment.setting;

import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;
import com.sasd13.proadmin.view.IBrowsable;
import com.sasd13.proadmin.view.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface ISettingController extends IController, IBrowsable {

    void actionUpdateTeacher(TeacherUpdateWrapper teacherUpdateWrapper);
}
