package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import proadmin.beans.Team;
import proadmin.db.TeamTableAccessor;

public class TeamDAO extends SQLiteTableDAO<Team> implements TeamTableAccessor {

    @Override
    protected ContentValues getContentValues(Team team) {
        return null;
    }

    @Override
    protected Team getCursorValues(Cursor cursor) {
        return null;
    }

    @Override
    public long insert(Team team) {
        return 0;
    }

    @Override
    public void update(Team team) {

    }

    @Override
    public void delete(Team team) {

    }

    @Override
    public Team select(long id) {
        return null;
    }

    @Override
    public List<Team> selectByRunningYear(long runningYear) {
        return null;
    }
}
