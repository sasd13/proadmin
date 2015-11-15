package proadmin.db.sqlite;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import proadmin.beans.Student;
import proadmin.beans.Team;
import proadmin.db.StudentTeamTableAccessor;

public class StudentTeamDAO implements StudentTeamTableAccessor {

    private SQLiteDatabase db;

    public StudentTeamDAO() {}

    public void setDB(SQLiteDatabase db) { this.db = db; }

    @Override
    public void insertStudentInTeam(long studentId, long teamId) {

    }

    @Override
    public void deleteStudentFromTeam(long studentId, long teamId) {

    }

    @Override
    public void deleteTeam(long teamId) {

    }

    @Override
    public List<Long> selectByTeam(long teamId) {
        return null;
    }
}
