package com.sasd13.proadmin.android.util.adapter.bean2itf.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.update.TeacherUpdate;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.teacher.CoreInfo;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

public class TeacherUpdateAdapterB2I implements IAdapter<TeacherUpdate, TeacherBean> {

    @Override
    public TeacherBean adapt(TeacherUpdate s) {
        TeacherBean t = new TeacherBean();

        Id id = new Id();
        id.setId(s.getIntermediary());
        t.setId(id);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setIntermediary(s.getWrapped().getIntermediary());
        coreInfo.setFirstName(s.getWrapped().getFirstName());
        coreInfo.setLastName(s.getWrapped().getLastName());
        coreInfo.setEmail(s.getWrapped().getEmail());
        t.setCoreInfo(coreInfo);

        return t;
    }
}
