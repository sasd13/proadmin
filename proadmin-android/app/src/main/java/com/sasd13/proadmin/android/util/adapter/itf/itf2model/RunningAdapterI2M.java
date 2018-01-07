package com.sasd13.proadmin.android.util.adapter.itf.itf2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.model.Project;
import com.sasd13.proadmin.android.model.Running;
import com.sasd13.proadmin.android.model.Teacher;
import com.sasd13.proadmin.itf.bean.running.RunningBean;

public class RunningAdapterI2M implements IAdapter<RunningBean, Running> {

    @Override
    public Running adapt(RunningBean s) {
        Running t = new Running();

        t.setId(Long.valueOf(s.getId().getId()));
        t.setYear(s.getCoreInfo().getYearStarted());

        Project project = new Project();
        project.setId(Long.valueOf(s.getLinkedProject().getId()));
        project.setCode(s.getLinkedProject().getCode());
        t.setProject(project);

        Teacher teacher = new Teacher();
        teacher.setId(Long.valueOf(s.getLinkedTeacher().getId()));
        teacher.setIntermediary(s.getLinkedTeacher().getIntermediary());
        t.setTeacher(teacher);

        return t;
    }
}
