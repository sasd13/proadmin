package com.sasd13.proadmin.service;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.TeacherUpdateWrapper;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface ITeacherService {

    Teacher read(String number);

    void update(TeacherUpdateWrapper teacherUpdateWrapper);
}
