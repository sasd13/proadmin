package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdate;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface ITeamService {

    ServiceResult<List<Team>> read(Map<String, String[]> parameters);

    ServiceResult<Void> create(Team team);

    ServiceResult<Void> update(TeamUpdate teamUpdate);

    ServiceResult<Void> delete(List<Team> teams);
}
