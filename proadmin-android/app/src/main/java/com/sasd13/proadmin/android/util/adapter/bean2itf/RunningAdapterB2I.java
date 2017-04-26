package com.sasd13.proadmin.android.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.itf.bean.running.CoreInfo;
import com.sasd13.proadmin.itf.bean.running.Id;
import com.sasd13.proadmin.itf.bean.running.LinkedId;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningAdapterB2I implements IAdapter<Running, RunningBean> {

    @Override
    public RunningBean adapt(Running s) {
        RunningBean t = new RunningBean();

        Id id = new Id();
        t.setId(id);

        LinkedId linkedId = new LinkedId();
        linkedId.setYearStarted(s.getYear());
        linkedId.setProjectCode(s.getProject().getCode());
        linkedId.setTeacherIntermediary(s.getTeacher().getIntermediary());
        id.setLinkedId(linkedId);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setYearStarted(s.getYear());
        t.setCoreInfo(coreInfo);

        return t;
    }
}