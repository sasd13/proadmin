package com.sasd13.proadmin.util.builder.member;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.gui.form.TeacherForm;
import com.sasd13.proadmin.util.builder.IBuilderFromForm;

/**
 * Created by ssaidali2 on 22/11/2016.
 */

public class TeacherFromFormBuilder implements IBuilderFromForm<Teacher> {

    private TeacherForm teacherForm;

    public TeacherFromFormBuilder(TeacherForm teacherForm) {
        this.teacherForm = teacherForm;
    }

    @Override
    public Teacher build() throws FormException {
        Teacher teacherFromForm = new Teacher(teacherForm.getNumber());

        teacherFromForm.setFirstName(teacherForm.getFirstName());
        teacherFromForm.setLastName(teacherForm.getLastName());
        teacherFromForm.setEmail(teacherForm.getEmail());

        return teacherFromForm;
    }
}
