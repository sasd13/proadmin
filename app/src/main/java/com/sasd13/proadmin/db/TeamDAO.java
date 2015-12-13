package com.sasd13.proadmin.db;

import com.sasd13.wsprovider.proadmin.bean.running.Team;

import java.util.List;

public interface TeamDAO {

    String TEAM_TABLE_NAME = "teams";

    String TEAM_ID = "team_id";
    String TEAM_CODE = "team_code";
    String RUNNINGS_RUNNING_ID = "runnings_running_id";

    long insert(Team team);

    void update(Team team);

    void delete(long id);

    Team select(long id);

    List<Team> selectByRunning(long runningId);
}
