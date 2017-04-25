package com.sasd13.proadmin.android.util.adapter.itf2bean.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.bean.update.TeacherUpdate;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

public class TeacherUpdateAdapterI2B implements IAdapter<TeacherBean, TeacherUpdate> {

    @Override
    public TeacherUpdate adapt(TeacherBean s) {
        TeacherUpdate t = new TeacherUpdate();

        t.setIntermediary(s.getId().getId());

        Teacher teacher = new Teacher();
        teacher.setIntermediary(s.getCoreInfo().getIntermediary());
        teacher.setFirstName(s.getCoreInfo().getFirstName());
        teacher.setLastName(s.getCoreInfo().getLastName());
        teacher.setEmail(s.getCoreInfo().getEmail());
        t.setWrapped(teacher);

        return t;
    }
}
