package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.sasd13.javaex.db.condition.ConditionBuilder;
import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.TeamDAO;
import com.sasd13.proadmin.dao.condition.TeamConditionExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteTeamDAO extends SQLiteEntityDAO<Team> implements TeamDAO {

    @Override
    protected ContentValues getContentValues(Team team) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, team.getId());
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
        return db.insert(TABLE, null, getContentValues(team));
    }

    @Override
    public void update(Team team) {
        db.update(TABLE, getContentValues(team), COLUMN_ID + " = ?", new String[]{ String.valueOf(team.getId()) });
    }

    @Override
    public void delete(Team team) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(TABLE);
        builder.append(" SET ");
        builder.append(COLUMN_DELETED + " = 1");
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = " + team.getId());

        try {
            db.execSQL(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Team select(long id) {
        Team team = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = ?");
        builder.append(" AND ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(id), String.valueOf(0) });
        if (cursor.moveToNext()) {
            team = getCursorValues(cursor);
        }
        cursor.close();

        return team;
    }

    @Override
    public List<Team> select(Map<String, String[]> parameters) {
        List<Team> list = new ArrayList<>();

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ");
            builder.append(TABLE);
            builder.append(" WHERE ");
            builder.append(ConditionBuilder.parse(parameters, TeamConditionExpression.class));
            builder.append(" AND ");
            builder.append(COLUMN_DELETED + " = ?");

            Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (ConditionBuilderException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Team> selectAll() {
        List<Team> list = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_DELETED + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(0) });
        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
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
