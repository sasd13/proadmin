package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.itf.bean.team.TeamBean;

public interface ITeamService {

	void create(TeamBean teamBean);

	void update(TeamBean teamBean);

	void delete(TeamBean teamBean);

	List<TeamBean> read(Map<String, Object> criterias);
}
