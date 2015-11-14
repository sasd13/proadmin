package proadmin.db;

import java.util.List;

import proadmin.beans.Student;
import proadmin.beans.Team;

public interface StudentTeamTableAccessor {

    String TABLE_NAME = "studentteams";

    String STUDENTS_STUDENT_ID = "students_student_id";
    String TEAMS_TEAM_ID = "teams_team_id";

    void insert(Student student, Team team);

    void delete(Team team);

    void deleteFromTeam(Student student, Team team);

    List<Student> selectByTeam(Team team);
}
