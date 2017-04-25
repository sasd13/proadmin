package com.sasd13.proadmin.android.util.adapter.bean2itf.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.update.RunningTeamUpdate;
import com.sasd13.proadmin.itf.bean.LinkedAcademicLevel;
import com.sasd13.proadmin.itf.bean.LinkedProject;
import com.sasd13.proadmin.itf.bean.LinkedRunning;
import com.sasd13.proadmin.itf.bean.LinkedTeacher;
import com.sasd13.proadmin.itf.bean.LinkedTeam;
import com.sasd13.proadmin.itf.bean.runningteam.Id;
import com.sasd13.proadmin.itf.bean.runningteam.LinkedId;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamUpdateAdapterB2I implements IAdapter<RunningTeamUpdate, RunningTeamBean> {

    @Override
    public RunningTeamBean adapt(RunningTeamUpdate s) {
        RunningTeamBean t = new RunningTeamBean();

        Id id = new Id();
        t.setId(id);

        LinkedId linkedId = new LinkedId();
        linkedId.setYearStarted(s.getRunningYear());
        linkedId.setProjectCode(s.getProjectCode());
        linkedId.setTeacherIntermediary(s.getTeacherIntermediary());
        linkedId.setTeamNumber(s.getTeamNumber());
        linkedId.setAcademicLevelCode(s.getAcademicLevelCode());
        id.setLinkedId(linkedId);

        LinkedRunning linkedRunning = new LinkedRunning();
        linkedRunning.setYearStarted(s.getWrapped().getRunning().getYear());
        t.setLinkedRunning(linkedRunning);

        LinkedProject linkedProject = new LinkedProject();
        linkedProject.setCode(s.getWrapped().getRunning().getProject().getCode());
        linkedRunning.setLinkedProject(linkedProject);

        LinkedTeacher linkedTeacher = new LinkedTeacher();
        linkedTeacher.setIntermediary(s.getWrapped().getRunning().getTeacher().getIntermediary());
        linkedRunning.setLinkedTeacher(linkedTeacher);

        LinkedTeam linkedTeam = new LinkedTeam();
        linkedTeam.setNumber(s.getWrapped().getTeam().getNumber());
        t.setLinkedTeam(linkedTeam);

        LinkedAcademicLevel linkedAcademicLevel = new LinkedAcademicLevel();
        linkedAcademicLevel.setCode(s.getWrapped().getAcademicLevel().getCode());
        t.setLinkedAcademicLevel(linkedAcademicLevel);

        return t;
    }
}
