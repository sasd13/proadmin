package com.sasd13.proadmin.android.util.adapter.bean2itf.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.update.RunningUpdate;
import com.sasd13.proadmin.itf.bean.LinkedProject;
import com.sasd13.proadmin.itf.bean.LinkedTeacher;
import com.sasd13.proadmin.itf.bean.running.CoreInfo;
import com.sasd13.proadmin.itf.bean.running.Id;
import com.sasd13.proadmin.itf.bean.running.LinkedId;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningUpdateAdapterB2I implements IAdapter<RunningUpdate, RunningBean> {

    @Override
    public RunningBean adapt(RunningUpdate s) {
        RunningBean t = new RunningBean();

        Id id = new Id();
        t.setId(id);

        LinkedId linkedId = new LinkedId();
        linkedId.setYearStarted(s.getYear());
        linkedId.setProjectCode(s.getProjectCode());
        linkedId.setTeacherIntermediary(s.getTeacherIntermediary());
        id.setLinkedId(linkedId);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setYearStarted(s.getYear());
        t.setCoreInfo(coreInfo);

        LinkedProject linkedProject = new LinkedProject();
        linkedProject.setCode(s.getWrapped().getProject().getCode());
        t.setLinkedProject(linkedProject);

        LinkedTeacher linkedTeacher = new LinkedTeacher();
        linkedTeacher.setIntermediary(s.getWrapped().getTeacher().getIntermediary());
        t.setLinkedTeacher(linkedTeacher);

        return t;
    }
}
