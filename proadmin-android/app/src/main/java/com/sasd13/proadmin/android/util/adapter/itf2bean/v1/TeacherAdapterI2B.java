package com.sasd13.proadmin.android.util.adapter.itf2bean.v1;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

public class TeacherAdapterI2B implements IAdapter<TeacherBean, Teacher> {

    @Override
    public Teacher adapt(TeacherBean s) {
        Teacher t = new Teacher();

        t.setIntermediary(s.getCoreInfo().getIntermediary());
        t.setFirstName(s.getCoreInfo().getFirstName());
        t.setLastName(s.getCoreInfo().getLastName());
        t.setEmail(s.getCoreInfo().getEmail());

        return t;
    }
}
