package com.sasd13.proadmin.core.db;

import java.util.List;

import com.sasd13.javaex.db.EntityDAO;
import com.sasd13.proadmin.core.bean.running.LeadEvaluation;

public interface LeadEvaluationDAO extends EntityDAO<LeadEvaluation> {
	
	String LEADEVALUATION_TABLE_NAME = "leadevaluations";
	
	String LEADEVALUATION_ID = "leadevaluation_id";
	String LEADEVALUATION_PLANNINGMARK = "leadevaluation_planningmark";
	String LEADEVALUATION_PLANNINGCOMMENT = "leadevaluation_planningcomment";
	String LEADEVALUATION_COMMUNICATIONMARK = "leadevaluation_communicationmark";
	String LEADEVALUATION_COMMUNICATIONCOMMENT = "leadevaluation_communicationcomment";
	String STUDENTS_STUDENT_ID = "students_student_id";
	String REPORTS_REPORT_ID = "reports_report_id";
	
	List<LeadEvaluation> selectByStudent(long studentId);
	
	LeadEvaluation selectByReport(long reportId);
}
