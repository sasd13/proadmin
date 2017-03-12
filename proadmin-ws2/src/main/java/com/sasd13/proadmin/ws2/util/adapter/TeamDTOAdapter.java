package com.sasd13.proadmin.ws2.util.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.ws2.db.dto.TeamDTO;

public class TeamDTOAdapter implements IAdapter<TeamDTO, Team> {

	@Override
	public Team adapt(TeamDTO source) {
		Team target = new Team();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(TeamDTO source, Team target) {
		target.setNumber(source.getNumber());
	}
}
