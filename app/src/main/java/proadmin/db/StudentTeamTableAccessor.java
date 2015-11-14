package proadmin.db;

import java.util.List;

import proadmin.beans.Student;
import proadmin.beans.Team;

public interface StudentTeamTableAccessor {

    String STUDENTTEAM_TABLE_NAME = "studentteams";

    String TEAMS_TEAM_ID = "teams_team_id";
    String STUDENTS_STUDENT_ID = "students_student_id";

    void insertStudentInTeam(Student student, Team team);

    void deleteStudentFromTeam(Student student, Team team);

    void deleteTeam(Team team);

    List<Student> selectByTeam(Team team);
}
