package com.sasd13.proadmin.wrapper.read.member;

import com.sasd13.proadmin.bean.member.Student;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class StudentReadWrapper implements IStudentReadWrapper {

    private List<Student> students;

    public StudentReadWrapper(List<Student> students) {
        this.students = students;
    }

    @Override
    public List<Student> getWrapped() {
        return students;
    }
}
