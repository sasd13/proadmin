package com.sasd13.proadmin.android.util.adapter.itf2bean.v2;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamAdapterI2B implements IAdapter<RunningTeamBean, RunningTeam> {

    @Override
    public RunningTeam adapt(RunningTeamBean s) {
        RunningTeam t = new RunningTeam();

        t.setId(Long.valueOf(s.getId().getId()));

        Running running = new Running();
        running.setId(Long.valueOf(s.getLinkedRunning().getId()));
        t.setRunning(running);

        Team team = new Team();
        team.setId(Long.valueOf(s.getLinkedTeam().getId()));
        t.setTeam(team);

        AcademicLevel academicLevel = new AcademicLevel();
        academicLevel.setId(Long.valueOf(s.getLinkedAcademicLevel().getId()));
        t.setAcademicLevel(academicLevel);

        return t;
    }
}
