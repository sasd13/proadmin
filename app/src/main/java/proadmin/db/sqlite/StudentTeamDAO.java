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
    public void insertStudentInTeam(Student student, Team team) {

    }

    @Override
    public void deleteStudentFromTeam(Student student, Team team) {

    }

    @Override
    public void deleteTeam(Team team) {

    }

    @Override
    public List<Student> selectByTeam(Team team) {
        return null;
    }
}
