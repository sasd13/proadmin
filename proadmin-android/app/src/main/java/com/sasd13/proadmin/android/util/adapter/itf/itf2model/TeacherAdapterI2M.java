package com.sasd13.proadmin.android.util.adapter.itf.itf2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.model.Teacher;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

public class TeacherAdapterI2M implements IAdapter<TeacherBean, Teacher> {

    @Override
    public Teacher adapt(TeacherBean s) {
        Teacher t = new Teacher();

        t.setId(Long.valueOf(s.getId().getId()));
        t.setIntermediary(s.getCoreInfo().getIntermediary());
        t.setFirstName(s.getCoreInfo().getFirstName());
        t.setLastName(s.getCoreInfo().getLastName());
        t.setEmail(s.getCoreInfo().getEmail());

        return t;
    }
}
