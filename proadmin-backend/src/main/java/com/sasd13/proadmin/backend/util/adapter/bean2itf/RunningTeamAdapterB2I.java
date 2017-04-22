package com.sasd13.proadmin.backend.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.RunningTeam;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamAdapterB2I implements IAdapter<RunningTeam, RunningTeamBean> {

	private RunningAdapterB2I runningAdapter;
	private TeamAdapterB2I teamAdapter;
	private AcademicLevelAdapterB2I academicLevelAdapter;

	public RunningTeamAdapterB2I() {
		runningAdapter = new RunningAdapterB2I();
		teamAdapter = new TeamAdapterB2I();
		academicLevelAdapter = new AcademicLevelAdapterB2I();
	}

	@Override
	public RunningTeamBean adapt(RunningTeam s) {
		RunningTeamBean t = new RunningTeamBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		t.setLinkedRunning(runningAdapter.adapt(s.getRunning()));
		t.setLinkedTeam(teamAdapter.adapt(s.getTeam()));
		t.setLinkedAcademicLevel(academicLevelAdapter.adapt(s.getAcademicLevel()));

		return t;
	}
}
