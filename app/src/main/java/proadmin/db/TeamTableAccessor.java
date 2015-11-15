package proadmin.db;

import java.util.List;

import proadmin.beans.running.Team;

public interface TeamTableAccessor {

    String TEAM_TABLE_NAME = "teams";

    String TEAM_ID = "team_id";
    String TEAM_RUNNINGYEAR = "team_runningyear";
    String TEAM_CODE = "team_code";
    String TEAM_DELETED = "team_deleted";

    long insert(Team team);

    void update(Team team);

    void delete(long id);

    Team select(long id);

    List<Team> selectByRunningYear(long runningYear);
}
