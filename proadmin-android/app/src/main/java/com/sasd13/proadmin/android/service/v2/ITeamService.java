package com.sasd13.proadmin.android.service.v2;

import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.service.ServiceResult;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public interface ITeamService {

    ServiceResult<List<Team>> read(Map<String, String[]> parameters);

    ServiceResult<Void> create(Team team);

    ServiceResult<Void> update(Team team);

    ServiceResult<Void> delete(List<Team> teams);
}
