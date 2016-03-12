package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.proadmin.core.bean.member.Team;
import com.sasd13.proadmin.core.db.TeamDAO;
import com.sasd13.proadmin.core.db.util.WhereClauseException;
import com.sasd13.proadmin.core.db.util.WhereClauseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteTeamDAO extends SQLiteEntityDAO<Team> implements TeamDAO {

    @Override
    protected ContentValues getContentValues(Team team) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_CODE, team.getCode());

        return values;
    }

    @Override
    protected Team getCursorValues(Cursor cursor) {
        Team team = new Team();

        team.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        team.setCode(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));

        return team;
    }

    @Override
    public long insert(Team team) {
        long id = db.insert(TABLE, null, getContentValues(team));

        if (id < 0) id = 0;

        team.setId(id);

        return id;
    }

    @Override
    public void update(Team team) {
        db.update(TABLE, getContentValues(team), COLUMN_ID + " = ?", new String[]{String.valueOf(team.getId())});
    }

    @Override
    public void delete(long id) {
        String query = "UPDATE " + TABLE
                + " SET "
                + COLUMN_DELETED + " = 1"
                + " WHERE "
                + COLUMN_ID + " = " + id;

        try {
            db.execSQL(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Team select(long id) {
        Team team = null;

        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                team = getCursorValues(cursor);
            }
        }
        cursor.close();

        return team;
    }

    @Override
    public List<Team> select(Map<String, String[]> parameters) {
        List<Team> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM " + TABLE
                    + " WHERE "
                    + WhereClauseParser.parse(TeamDAO.class, parameters);

            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                    list.add(getCursorValues(cursor));
                }
            }
            cursor.close();
        } catch (WhereClauseException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Team> selectAll() {
        List<Team> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(COLUMN_DELETED)) == 0) {
                list.add(getCursorValues(cursor));
            }
        }
        cursor.close();

        return list;
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
