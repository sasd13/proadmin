package com.sasd13.proadmin.android.util.adapter.bean2itf.v1.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.update.StudentUpdate;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.student.CoreInfo;
import com.sasd13.proadmin.itf.bean.student.StudentBean;

public class StudentUpdateAdapterB2I implements IAdapter<StudentUpdate, StudentBean> {

    @Override
    public StudentBean adapt(StudentUpdate s) {
        StudentBean t = new StudentBean();

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
