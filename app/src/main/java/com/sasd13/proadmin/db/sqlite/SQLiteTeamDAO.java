package com.sasd13.proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.TeamDAO;

import java.util.ArrayList;
import java.util.List;

public class SQLiteTeamDAO extends SQLiteTableDAO<Team> implements TeamDAO {

    @Override
    protected ContentValues getContentValues(Team team) {
        ContentValues values = new ContentValues();

        values.put(TEAM_CODE, team.getCode());
        values.put(RUNNINGS_RUNNING_ID, team.getRunning().getId());

        return values;
    }

    @Override
    protected Team getCursorValues(Cursor cursor) {
        Team team = new Team();

        team.setId(cursor.getLong(cursor.getColumnIndex(TEAM_ID)));
        team.setCode(cursor.getString(cursor.getColumnIndex(TEAM_CODE)));

        Running running = new Running();
        running.setId(cursor.getLong(cursor.getColumnIndex(RUNNINGS_RUNNING_ID)));
        team.setRunning(running);

        return team;
    }

    @Override
    public long insert(Team team) {
        return getDB().insert(TEAM_TABLE_NAME, null, getContentValues(team));
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
    public List<Team> selectByRunning(long runningId) {
        List<Team> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEAM_TABLE_NAME
                        + " where " + RUNNINGS_RUNNING_ID + " = ?", new String[]{String.valueOf(runningId)});

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public List<Team> selectAll() {
        List<Team> list = new ArrayList<>();

        Cursor cursor = getDB().rawQuery(
                "select *"
                        + " from " + TEAM_TABLE_NAME, null);

        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }
}
