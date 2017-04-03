package com.sasd13.proadmin.util.scope;

import com.sasd13.proadmin.bean.member.Teacher;

import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class TeacherWrapper extends Observable {

    private Teacher teacher;

    public TeacherWrapper(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
