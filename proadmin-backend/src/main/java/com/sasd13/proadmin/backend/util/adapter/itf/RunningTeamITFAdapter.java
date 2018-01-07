package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.RunningTeam;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.RunningTeamAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.RunningTeamAdapterM2I;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class RunningTeamITFAdapter extends ITFAdapter<RunningTeam, RunningTeamBean> {

	public RunningTeamITFAdapter() {
		super(new RunningTeamAdapterI2M(), new RunningTeamAdapterM2I());
	}
}
