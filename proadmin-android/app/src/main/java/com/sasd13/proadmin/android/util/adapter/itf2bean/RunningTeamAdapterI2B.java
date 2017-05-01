package com.sasd13.proadmin.android.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamAdapterI2B implements IAdapter<RunningTeamBean, RunningTeam> {

    @Override
    public RunningTeam adapt(RunningTeamBean s) {
        RunningTeam t = new RunningTeam();

        t.setId(Long.valueOf(s.getId().getId()));

        Running running = new Running();
        running.setId(Long.valueOf(s.getLinkedRunning().getId()));
        running.setYear(s.getLinkedRunning().getYearStarted());
        t.setRunning(running);

        Project project = new Project();
        project.setId(Long.valueOf(s.getLinkedRunning().getLinkedProject().getId()));
        project.setCode(s.getLinkedRunning().getLinkedProject().getCode());
        running.setProject(project);

        Teacher teacher = new Teacher();
        teacher.setId(Long.valueOf(s.getLinkedRunning().getLinkedTeacher().getId()));
        teacher.setIntermediary(s.getLinkedRunning().getLinkedTeacher().getIntermediary());
        running.setTeacher(teacher);

        Team team = new Team();
        team.setId(Long.valueOf(s.getLinkedTeam().getId()));
        team.setNumber(s.getLinkedTeam().getNumber());
        t.setTeam(team);

        AcademicLevel academicLevel = new AcademicLevel();
        academicLevel.setId(Long.valueOf(s.getLinkedAcademicLevel().getId()));
        academicLevel.setCode(s.getLinkedAcademicLevel().getCode());
        t.setAcademicLevel(academicLevel);

        return t;
    }
}
