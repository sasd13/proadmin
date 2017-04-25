package com.sasd13.proadmin.android.bean.update;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.android.bean.Student;

public class StudentUpdate implements IUpdateWrapper<Student> {

    private Student student;
    private String intermediary;

    @Override
    public Student getWrapped() {
        return student;
    }

    @Override
    public void setWrapped(Student student) {
        this.student = student;
    }

    public String getIntermediary() {
        return intermediary;
    }

    public void setIntermediary(String intermediary) {
        this.intermediary = intermediary;
    }
}
