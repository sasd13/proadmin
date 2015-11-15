package proadmin.db;

import java.util.List;

public interface StudentTeamTableAccessor {

    String STUDENTTEAM_TABLE_NAME = "studentteams";

    String TEAMS_TEAM_ID = "teams_team_id";
    String STUDENTS_STUDENT_ID = "students_student_id";

    long insertStudentInTeam(long studentId, long teamId);

    void deleteStudentFromTeam(long studentId, long teamId);

    void deleteStudentsFromTeam(long teamId);

    List<Long> selectByTeam(long teamId);
}
