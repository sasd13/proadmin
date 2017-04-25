package com.sasd13.proadmin.android.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningAdapterI2B implements IAdapter<RunningBean, Running> {

    @Override
    public Running adapt(RunningBean s) {
        Running t = new Running();

        t.setYear(s.getCoreInfo().getYearStarted());

        Project project = new Project();
        project.setCode(s.getLinkedProject().getCode());
        t.setProject(project);

        Teacher teacher = new Teacher();
        teacher.setIntermediary(s.getLinkedTeacher().getIntermediary());
        t.setTeacher(teacher);

        return t;
    }
}
