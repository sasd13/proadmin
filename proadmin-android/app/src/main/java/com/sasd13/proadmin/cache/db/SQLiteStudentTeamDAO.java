package com.sasd13.proadmin.cache.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.StudentTeamDAO;
import com.sasd13.proadmin.dao.condition.ConditionBuilder;
import com.sasd13.proadmin.dao.condition.ConditionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLiteStudentTeamDAO extends SQLiteEntityDAO<StudentTeam> implements StudentTeamDAO {

    @Override
    protected ContentValues getContentValues(StudentTeam studentTeam) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, studentTeam.getId());
        values.put(COLUMN_STUDENT_ID, studentTeam.getStudent().getId());
        values.put(COLUMN_TEAM_ID, studentTeam.getTeam().getId());

        return values;
    }

    protected StudentTeam getCursorValues(Cursor cursor) {
        Student student = new Student();
        student.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_ID)));

        Team team = new Team();
        team.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_TEAM_ID)));

        StudentTeam studentTeam = new StudentTeam(student, team);
        studentTeam.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));

        return studentTeam;
    }

    @Override
    public long insert(StudentTeam studentTeam) {
        return db.insert(TABLE, null, getContentValues(studentTeam));
    }

    @Override
    public void update(StudentTeam studentTeam) {
        //Do nothing
    }

    @Override
    public void delete(StudentTeam studentTeam) {
        db.delete(TABLE, COLUMN_ID + " = ?", new String[]{ String.valueOf(studentTeam.getId()) });
    }

    @Override
    public StudentTeam select(long id) {
        StudentTeam studentTeam = null;

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT * FROM ");
        builder.append(TABLE);
        builder.append(" WHERE ");
        builder.append(COLUMN_ID + " = ?");

        Cursor cursor = db.rawQuery(builder.toString(), new String[]{ String.valueOf(id) });
        if (cursor.moveToNext()) {
            studentTeam = getCursorValues(cursor);
        }
        cursor.close();

        return studentTeam;
    }

    @Override
    public List<StudentTeam> select(Map<String, String[]> parameters) {
        List<StudentTeam> list = new ArrayList<>();

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM ");
            builder.append(TABLE);
            builder.append(" WHERE ");
            builder.append(ConditionBuilder.parse(parameters, StudentTeamDAO.class));

            Cursor cursor = db.rawQuery(builder.toString(), null);
            while (cursor.moveToNext()) {
                list.add(getCursorValues(cursor));
            }
            cursor.close();
        } catch (ConditionException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<StudentTeam> selectAll() {
        List<StudentTeam> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            list.add(getCursorValues(cursor));
        }
        cursor.close();

        return list;
    }

    @Override
    public void persist(StudentTeam studentTeam) {
        if (select(studentTeam.getId()) == null) {
            insert(studentTeam);
        } else {
            update(studentTeam);
        }
    }
}
