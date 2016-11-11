package com.sasd13.proadmin.wrapper.read.member;

import com.sasd13.proadmin.bean.member.Teacher;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class TeacherReadWrapper implements ITeacherReadWrapper {

    private List<Teacher> teachers;

    public TeacherReadWrapper(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public List<Teacher> getWrapped() {
        return teachers;
    }
}
