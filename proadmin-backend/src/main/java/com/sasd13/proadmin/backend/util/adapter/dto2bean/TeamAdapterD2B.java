package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Team;
import com.sasd13.proadmin.backend.dao.dto.TeamDTO;

public class TeamAdapterD2B implements IAdapter<TeamDTO, Team> {

	@Override
	public Team adapt(TeamDTO s) {
		Team t = new Team();

		t.setId(s.getId());
		t.setNumber(s.getNumber());
		t.setName(s.getName());

		return t;
	}
}
