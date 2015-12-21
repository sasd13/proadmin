package com.sasd13.proadmin.core.db;

import java.util.List;

import com.sasd13.javaex.db.EntityDAO;
import com.sasd13.proadmin.core.bean.running.Running;

public interface RunningDAO extends EntityDAO<Running> {
	
	String RUNNING_TABLE_NAME = "runnings";
	
	String RUNNING_ID = "running_id";
	String RUNNING_YEAR = "running_year";
	String TEACHERS_TEACHER_ID = "teachers_teacher_id";
	String PROJECTS_PROJECT_ID = "projects_project_id";
	
	List<Running> selectByYear(int year);
	
	List<Running> selectByTeacher(long teacherId);
	
	List<Running> selectByProject(long projectId);
}
