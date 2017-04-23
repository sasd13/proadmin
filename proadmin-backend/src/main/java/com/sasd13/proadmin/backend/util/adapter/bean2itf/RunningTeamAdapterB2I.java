package com.sasd13.proadmin.backend.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedInfo;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamAdapterB2I implements IAdapter<RunningTeam, RunningTeamBean> {

	@Override
	public RunningTeamBean adapt(RunningTeam s) {
		RunningTeamBean t = new RunningTeamBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		LinkedInfo linkedRunning = new LinkedInfo();
		linkedRunning.setId(String.valueOf(s.getRunning().getId()));
		t.setLinkedRunning(linkedRunning);

		LinkedInfo linkedTeam = new LinkedInfo();
		linkedTeam.setId(String.valueOf(s.getTeam().getId()));
		t.setLinkedTeam(linkedTeam);

		LinkedInfo linkedAcademicLevel = new LinkedInfo();
		linkedAcademicLevel.setId(String.valueOf(s.getAcademicLevel().getId()));
		t.setLinkedAcademicLevel(linkedAcademicLevel);

		return t;
	}
}
