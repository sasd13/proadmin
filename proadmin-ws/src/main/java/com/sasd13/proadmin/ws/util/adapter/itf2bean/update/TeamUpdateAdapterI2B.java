package com.sasd13.proadmin.ws.util.adapter.itf2bean.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.team.TeamBean;
import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.bean.update.TeamUpdate;

public class TeamUpdateAdapterI2B implements IAdapter<TeamBean, TeamUpdate> {

	@Override
	public TeamUpdate adapt(TeamBean s) {
		TeamUpdate t = new TeamUpdate();

		t.setNumber(s.getId().getId());

		Team team = new Team();

		team.setNumber(s.getCoreInfo().getNumber());
		team.setName(s.getCoreInfo().getName());
		t.setWrapped(team);

		return t;
	}
}
