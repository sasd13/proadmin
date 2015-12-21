package com.sasd13.proadmin.core.db;

import java.util.List;

public interface StudentTeamDAO {

    String STUDENTTEAM_TABLE_NAME = "studentteams";

    String TEAMS_TEAM_ID = "teams_team_id";
    String STUDENTS_STUDENT_ID = "students_student_id";

    long insert(long studentId, long teamId);
    
    void delete(long studentId, long teamId);

    List<Long> selectByTeam(long teamId);
    
    List<Long> selectByStudent(long studentId);
}
