package com.sasd13.proadmin.android.util.adapter.bean2itf.v2;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.itf.bean.LinkedAcademicLevel;
import com.sasd13.proadmin.itf.bean.LinkedRunning;
import com.sasd13.proadmin.itf.bean.LinkedTeam;
import com.sasd13.proadmin.itf.bean.runningteam.Id;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamAdapterB2I implements IAdapter<RunningTeam, RunningTeamBean> {

    @Override
    public RunningTeamBean adapt(RunningTeam s) {
        RunningTeamBean t = new RunningTeamBean();

        Id id = new Id();
        id.setId(String.valueOf(s.getId()));
        t.setId(id);

        LinkedRunning linkedRunning = new LinkedRunning();
        linkedRunning.setId(String.valueOf(s.getRunning().getId()));
        t.setLinkedRunning(linkedRunning);

        LinkedTeam linkedTeam = new LinkedTeam();
        linkedTeam.setId(String.valueOf(s.getTeam().getId()));
        t.setLinkedTeam(linkedTeam);

        LinkedAcademicLevel linkedAcademicLevel = new LinkedAcademicLevel();
        linkedAcademicLevel.setId(String.valueOf(s.getAcademicLevel().getId()));
        t.setLinkedAcademicLevel(linkedAcademicLevel);

        return t;
    }
}
