package com.sasd13.proadmin.backend.util.adapter.itf.itf2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.model.Team;
import com.sasd13.proadmin.itf.bean.team.TeamBean;

public class TeamAdapterI2M implements IAdapter<TeamBean, Team> {

	@Override
	public Team adapt(TeamBean s) {
		Team t = new Team();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		t.setNumber(s.getCoreInfo().getNumber());
		t.setName(s.getCoreInfo().getName());

		return t;
	}
}
