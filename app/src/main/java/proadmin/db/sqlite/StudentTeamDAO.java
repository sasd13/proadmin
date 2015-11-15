package proadmin.db.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proadmin.db.StudentTeamTableAccessor;

public class StudentTeamDAO implements StudentTeamTableAccessor {

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
    public void deleteStudentFromTeam(long studentId, long teamId) {
        db.delete(STUDENTTEAM_TABLE_NAME, STUDENTS_STUDENT_ID + " = ? and" + TEAMS_TEAM_ID + " = ?", new String[]{String.valueOf(studentId), String.valueOf(teamId)});
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
}
