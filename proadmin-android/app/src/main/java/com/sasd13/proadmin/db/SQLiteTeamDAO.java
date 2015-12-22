package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.TeamDAO;

import java.util.List;

public class SQLiteTeamDAO extends SQLiteEntityDAO<Team> implements TeamDAO {

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
        long id = executeInsert(TEAM_TABLE_NAME, team);

        team.setId(id);

        return id;
    }

    @Override
    public void update(Team team) {
        executeUpdate(TEAM_TABLE_NAME, team, TEAM_ID, team.getId());
    }

    @Override
    public void delete(long id) {
        executeDelete(TEAM_TABLE_NAME, TEAM_ID, id);
    }

    @Override
    public Team select(long id) {
        String query = "select * from " + TEAM_TABLE_NAME
                + " where "
                    + TEAM_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Team> selectAll() {
        String query = "select * from " + TEAM_TABLE_NAME;

        return executeSelectAll(query);
    }

    @Override
    public Team selectByCode(String code) {
        String query = "select * from " + TEAM_TABLE_NAME
                + " where "
                    + TEAM_CODE + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(code)});

        return executeSelectSingleResult(cursor);
    }

    @Override
    public List<Team> selectByRunning(long runningId) {
        String query = "select * from " + TEAM_TABLE_NAME
                + " where "
                    + RUNNINGS_RUNNING_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(runningId)});

        return executeSelectMultiResult(cursor);
    }
}
