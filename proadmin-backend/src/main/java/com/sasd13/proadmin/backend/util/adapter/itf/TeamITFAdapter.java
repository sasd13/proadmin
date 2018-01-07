package com.sasd13.proadmin.backend.util.adapter.itf;

import com.sasd13.proadmin.backend.model.Team;
import com.sasd13.proadmin.backend.util.adapter.itf.itf2model.TeamAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.itf.model2itf.TeamAdapterM2I;
import com.sasd13.proadmin.itf.bean.team.TeamBean;

public class TeamITFAdapter extends ITFAdapter<Team, TeamBean> {

	public TeamITFAdapter() {
		super(new TeamAdapterI2M(), new TeamAdapterM2I());
	}
}
