package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Team;
import com.sasd13.proadmin.backend.dao.dto.TeamDTO;

public class TeamAdapterB2D implements IAdapter<Team, TeamDTO> {

	@Override
	public TeamDTO adapt(Team s) {
		TeamDTO t = new TeamDTO();

		t.setId(s.getId());
		t.setNumber(s.getNumber());
		t.setName(s.getName());

		return t;
	}
}
