package com.sasd13.proadmin.android.util.adapter.bean2itf.v2;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.teacher.CoreInfo;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

public class TeacherAdapterB2I implements IAdapter<Teacher, TeacherBean> {

    @Override
    public TeacherBean adapt(Teacher s) {
        TeacherBean t = new TeacherBean();

        Id id = new Id();
        id.setId(String.valueOf(s.getId()));
        t.setId(id);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setIntermediary(s.getIntermediary());
        coreInfo.setFirstName(s.getFirstName());
        coreInfo.setLastName(s.getLastName());
        coreInfo.setEmail(s.getEmail());
        t.setCoreInfo(coreInfo);

        return t;
    }
}
