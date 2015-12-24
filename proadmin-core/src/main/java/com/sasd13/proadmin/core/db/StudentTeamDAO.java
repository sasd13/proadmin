package com.sasd13.proadmin.core.db;

import java.util.List;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.bean.running.StudentTeam;

public interface StudentTeamDAO extends IEntityDAO<StudentTeam> {
	
	String STUDENTTEAM_TABLE_NAME = "studentteams";
	
	String STUDENTTEAM_ID = "studentteam_id";
	String TEAMS_TEAM_ID = "teams_team_id";
	String STUDENTS_STUDENT_ID = "students_student_id";
	
	List<StudentTeam> selectByTeam(long teamId);
	
	List<StudentTeam> selectByStudent(long studentId);
}
