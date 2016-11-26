package com.sasd13.proadmin.ws.wrapper.member;

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.ws.wrapper.IReadWrapper;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class TeacherReadWrapper implements IReadWrapper<Teacher> {

    private Teacher teacher;

    public TeacherReadWrapper(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public Teacher getWrapped() {
        return teacher;
    }
}
