package com.sasd13.proadmin.util.builder.member;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.gui.form.StudentForm;
import com.sasd13.proadmin.util.builder.IBuilderFromForm;

/**
 * Created by ssaidali2 on 22/11/2016.
 */

public class StudentFromFormBuilder implements IBuilderFromForm<Student> {

    private StudentForm studentForm;

    public StudentFromFormBuilder(StudentForm studentForm) {
        this.studentForm = studentForm;
    }

    @Override
    public Student build() throws FormException {
        Student studentFromForm = new Student(studentForm.getNumber());

        studentFromForm.setFirstName(studentForm.getFirstName());
        studentFromForm.setLastName(studentForm.getLastName());
        studentFromForm.setEmail(studentForm.getEmail());

        return studentFromForm;
    }
}
