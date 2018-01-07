package com.sasd13.proadmin.android.service;

import com.sasd13.proadmin.android.model.Team;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface ITeamService {

    ServiceResult<List<Team>> read(Map<String, Object> criterias);

    ServiceResult<Void> create(Team team);

    ServiceResult<Void> update(Team team);

    ServiceResult<Void> delete(List<Team> teams);
}
