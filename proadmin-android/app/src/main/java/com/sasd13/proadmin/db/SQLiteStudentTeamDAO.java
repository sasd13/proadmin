package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sasd13.proadmin.core.db.StudentTeamDAO;

import java.util.ArrayList;
import java.util.List;

public class SQLiteStudentTeamDAO implements StudentTeamDAO {

    private SQLiteDatabase db;

    public void setDB(SQLiteDatabase db) { this.db = db; }

    protected ContentValues getContentValues(long studentId, long teamId) {
        ContentValues values = new ContentValues();

        values.put(TEAMS_TEAM_ID, teamId);
        values.put(STUDENTS_STUDENT_ID, studentId);

        return values;
    }

    @Override
    public long insertStudentInTeam(long studentId, long teamId) {
        return db.insert(STUDENTTEAM_TABLE_NAME, null, getContentValues(studentId, teamId));
    }

    @Override
    public void deleteByTeam(long id) {
        db.delete(STUDENTTEAM_TABLE_NAME, TEAMS_TEAM_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public List<Long> selectByTeam(long teamId) {
        List<Long> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + STUDENTS_STUDENT_ID
                        + " where " + TEAMS_TEAM_ID + " = ?", new String[]{String.valueOf(teamId)});

        while (cursor.moveToNext()) {
            list.add(cursor.getLong(cursor.getColumnIndex(STUDENTS_STUDENT_ID)));
        }
        cursor.close();

        return list;
    }

    @Override
    public List<Long> selectByStudent(long studentId) {
        List<Long> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "select *"
                        + " from " + STUDENTS_STUDENT_ID
                        + " where " + STUDENTS_STUDENT_ID + " = ?", new String[]{String.valueOf(studentId)});

        while (cursor.moveToNext()) {
            list.add(cursor.getLong(cursor.getColumnIndex(STUDENTS_STUDENT_ID)));
        }
        cursor.close();

        return list;
    }
}
