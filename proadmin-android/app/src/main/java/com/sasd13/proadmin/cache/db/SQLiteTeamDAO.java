package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.TeamDAO;

import java.util.List;
import java.util.Map;

public class SQLiteTeamDAO extends SQLiteEntityDAO<Team> implements TeamDAO {

    @Override
    protected ContentValues getContentValues(Team team) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_CODE, team.getCode());
        values.put(COLUMN_RUNNING_ID, team.getRunning().getId());

        return values;
    }

    @Override
    protected Team getCursorValues(Cursor cursor) {
        Team team = new Team();

        team.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        team.setCode(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));

        Running running = new Running();
        running.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_RUNNING_ID)));
        team.setRunning(running);

        return team;
    }

    @Override
    public long insert(Team team) {
        long id = executeInsert(TABLE, team);

        team.setId(id);

        return id;
    }

    @Override
    public void update(Team team) {
        executeUpdate(TABLE, team, COLUMN_ID, team.getId());
    }

    @Override
    public void delete(long id) {
        //Do nothing
    }

    @Override
    public Team select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<Team> select(Map<String, String[]> map) {
        return null;
    }

    @Override
    public List<Team> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
    }

    @Override
    public void persist(Team team) {
        if (select(team.getId()) == null) {
            insert(team);
        } else {
            update(team);
        }
    }
}
