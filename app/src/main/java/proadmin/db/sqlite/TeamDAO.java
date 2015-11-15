package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import proadmin.beans.running.Team;
import proadmin.db.TeamTableAccessor;

public class TeamDAO extends SQLiteTableDAO<Team> implements TeamTableAccessor {

    @Override
    protected ContentValues getContentValues(Team team) {
        ContentValues values = new ContentValues();

        //values.put(TEAM_ID, team.getId()); //autoincrement
        values.put(TEAM_RUNNINGYEAR, team.getRunningYear());
        values.put(TEAM_CODE, team.getCode());

        return values;
    }

    @Override
    protected Team getCursorValues(Cursor cursor) {
        Team team = new Team();

        team.setId(cursor.getLong(cursor.getColumnIndex(TEAM_ID)));
        team.setRunningYear(cursor.getLong(cursor.getColumnIndex(TEAM_RUNNINGYEAR)));
        team.setCode(cursor.getString(cursor.getColumnIndex(TEAM_CODE)));

        return team;
    }

    @Override
    public long insert(Team team) {
        ContentValues values = getContentValues(team);
        values.put(TEAM_DELETED, false);

        return getDB().insert(TEAM_TABLE_NAME, null, values);
    }

    @Override
    public void update(Team team) {
        getDB().update(TEAM_TABLE_NAME, getContentValues(team), TEAM_ID + " = ?", new String[]{String.valueOf(team.getId())});
    }

    @Override
    public void delete(long id) {
        getDB().delete(TEAM_TABLE_NAME, TEAM_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public Team select(long id) {
        Team team = null;

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEAM_TABLE_NAME
                        + " where " + TEAM_ID + " = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            team = getCursorValues(cursor);
        }
        cursor.close();

        return team;
    }

    @Override
    public List<Team> selectByRunningYear(long runningYear) {
        List<Team> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEAM_TABLE_NAME
                        + " where " + TEAM_RUNNINGYEAR + " = ?", new String[]{String.valueOf(runningYear)});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
