package com.sasd13.proadmin.android.util.adapter.itf2bean.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Project;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Teacher;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.bean.update.RunningTeamUpdate;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamUpdateAdapterI2B implements IAdapter<RunningTeamBean, RunningTeamUpdate> {

    @Override
    public RunningTeamUpdate adapt(RunningTeamBean s) {
        RunningTeamUpdate t = new RunningTeamUpdate();

        t.setRunningYear(s.getId().getLinkedId().getYearStarted());
        t.setProjectCode(s.getId().getLinkedId().getProjectCode());
        t.setTeacherIntermediary(s.getId().getLinkedId().getTeacherIntermediary());
        t.setTeamNumber(s.getId().getLinkedId().getTeamNumber());
        t.setAcademicLevelCode(s.getId().getLinkedId().getAcademicLevelCode());

        RunningTeam runningTeam = new RunningTeam();
        t.setWrapped(runningTeam);

        Running running = new Running();
        running.setYear(s.getLinkedRunning().getYearStarted());
        runningTeam.setRunning(running);

        Project project = new Project();
        project.setCode(s.getLinkedRunning().getLinkedProject().getCode());
        running.setProject(project);

        Teacher teacher = new Teacher();
        teacher.setIntermediary(s.getLinkedRunning().getLinkedTeacher().getIntermediary());
        running.setTeacher(teacher);

        Team team = new Team();
        team.setNumber(s.getLinkedTeam().getNumber());
        runningTeam.setTeam(team);

        AcademicLevel academicLevel = new AcademicLevel();
        academicLevel.setCode(s.getLinkedAcademicLevel().getCode());
        runningTeam.setAcademicLevel(academicLevel);

        return t;
    }
}
