package com.sasd13.proadmin.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.StudentTeam;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.StudentTeamDAO;

import java.util.List;

public class SQLiteStudentTeamDAO extends SQLiteEntityDAO<StudentTeam> implements StudentTeamDAO {

    @Override
    protected ContentValues getContentValues(StudentTeam studentTeam) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_STUDENT_ID, studentTeam.getStudent().getId());
        values.put(COLUMN_TEAM_ID, studentTeam.getTeam().getId());

        return values;
    }

    protected StudentTeam getCursorValues(Cursor cursor) {
        StudentTeam studentTeam = new StudentTeam();

        studentTeam.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));

        Student student = new Student();
        student.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_ID)));
        studentTeam.setStudent(student);

        Team team = new Team();
        team.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_TEAM_ID)));
        studentTeam.setTeam(team);

        return studentTeam;
    }

    @Override
    public long insert(StudentTeam studentTeam) {
        long id = executeInsert(TABLE, studentTeam);

        studentTeam.setId(id);

        return id;
    }

    @Override
    public void update(StudentTeam studentTeam) {
        //Do nothing
    }

    @Override
    public void delete(long id) {
        executeDelete(TABLE, COLUMN_ID, id);
    }

    @Override
    public StudentTeam select(long id) {
        String query = "SELECT * FROM " + TABLE
                + " WHERE "
                    + COLUMN_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<StudentTeam> selectAll() {
        String query = "SELECT * FROM " + TABLE;

        return executeSelectAll(query);
    }
}
