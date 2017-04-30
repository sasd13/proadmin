package com.sasd13.proadmin.android.util.adapter.itf2bean.v2;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningAdapterI2B implements IAdapter<RunningBean, Running> {

    @Override
    public Running adapt(RunningBean s) {
        Running t = new Running();

        t.setId(Long.valueOf(s.getId().getId()));
        t.setYear(s.getCoreInfo().getYearStarted());

        Project project = new Project();
        project.setId(Long.valueOf(s.getLinkedProject().getId()));
        t.setProject(project);

        Teacher teacher = new Teacher();
        teacher.setId(Long.valueOf(s.getLinkedTeacher().getId()));
        t.setTeacher(teacher);

        return t;
    }
}
