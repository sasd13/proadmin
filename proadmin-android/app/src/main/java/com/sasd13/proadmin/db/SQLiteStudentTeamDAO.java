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

        values.put(STUDENTS_STUDENT_ID, studentTeam.getStudent().getId());
        values.put(TEAMS_TEAM_ID, studentTeam.getTeam().getId());

        return values;
    }

    protected StudentTeam getCursorValues(Cursor cursor) {
        StudentTeam studentTeam = new StudentTeam();

        studentTeam.setId(cursor.getLong(cursor.getColumnIndex(STUDENTTEAM_ID)));

        Student student = new Student();
        student.setId(cursor.getLong(cursor.getColumnIndex(STUDENTS_STUDENT_ID)));
        studentTeam.setStudent(student);

        Team team = new Team();
        team.setId(cursor.getLong(cursor.getColumnIndex(TEAMS_TEAM_ID)));
        studentTeam.setTeam(team);

        return studentTeam;
    }

    @Override
    public long insert(StudentTeam studentTeam) {
        long id = executeInsert(STUDENTTEAM_TABLE_NAME, studentTeam);

        studentTeam.setId(id);

        return id;
    }

    @Override
    public void update(StudentTeam studentTeam) {
        //Do nothing
    }

    @Override
    public void delete(long id) {
        executeDelete(STUDENTTEAM_TABLE_NAME, STUDENTTEAM_ID, id);
    }

    @Override
    public StudentTeam select(long id) {
        String query = "select * from " + STUDENTTEAM_TABLE_NAME
                + " where "
                    + STUDENTTEAM_ID + " = ?";

        return executeSelectById(query, id);
    }

    @Override
    public List<StudentTeam> selectAll() {
        //Do nothing
        return null;
    }

    @Override
    public List<StudentTeam> selectByStudent(long studentId) {
        String query = "select * from " + STUDENTTEAM_TABLE_NAME
                + " where "
                    + STUDENTS_STUDENT_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(studentId)});

        return executeSelectMultiResult(cursor);
    }

    @Override
    public List<StudentTeam> selectByTeam(long teamId) {
        String query = "select * from " + STUDENTTEAM_TABLE_NAME
                + " where "
                    + TEAMS_TEAM_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(teamId)});

        return executeSelectMultiResult(cursor);
    }
}
