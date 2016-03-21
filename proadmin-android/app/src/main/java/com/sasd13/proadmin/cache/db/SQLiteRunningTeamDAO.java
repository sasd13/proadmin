package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.RunningTeamDAO;
import com.sasd13.proadmin.dao.util.WhereClauseException;
import com.sasd13.proadmin.dao.util.WhereClauseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteRunningTeamDAO extends SQLiteEntityDAO<RunningTeam> implements RunningTeamDAO {

    @Override
    protected ContentValues getContentValues(RunningTeam runningTeam) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, runningTeam.getId());
        values.put(COLUMN_RUNNING_ID, runningTeam.getRunning().getId());
        values.put(COLUMN_TEAM_ID, runningTeam.getTeam().getId());

        return values;
    }

    protected RunningTeam getCursorValues(Cursor cursor) {
        Running running = new Running();
        running.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_RUNNING_ID)));

        Team team = new Team();
        team.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_TEAM_ID)));

        RunningTeam runningTeam = new RunningTeam(running, team);
        runningTeam.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));

        return runningTeam;
    }

    @Override
    public long insert(RunningTeam runningTeam) {
        return db.insert(TABLE, null, getContentValues(runningTeam));
    }

    @Override
    public void update(RunningTeam runningTeam) {
        //Do nothing
    }

    @Override
    public void delete(long id) {
        db.delete(TABLE, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public RunningTeam select(long id) {
        RunningTeam runningTeam = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            runningTeam = getCursorValues(cursor);
        }
        cursor.close();

        return runningTeam;
    }

    @Override
    public List<RunningTeam> select(Map<String, String[]> parameters) {
        List<RunningTeam> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE "
                        + WhereClauseParser.parse(RunningTeamDAO.class, parameters);

            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (WhereClauseException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<RunningTeam> selectAll() {
        List<RunningTeam> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public void persist(RunningTeam runningTeam) {
        if (select(runningTeam.getId()) == null) {
            insert(runningTeam);
        } else {
            update(runningTeam);
        }
    }
}
