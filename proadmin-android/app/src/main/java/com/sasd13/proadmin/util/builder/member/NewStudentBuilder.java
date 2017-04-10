package com.sasd13.proadmin.util.builder.member;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.Student;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class NewStudentBuilder implements IBuilder<Student> {

    @Override
    public Student build() {
        Student student = new Student();

        return student;
    }
}
