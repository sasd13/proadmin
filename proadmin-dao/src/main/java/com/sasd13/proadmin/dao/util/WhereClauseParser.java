package com.sasd13.proadmin.dao.util;

import java.util.Map;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.dao.IndividualEvaluationDAO;
import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.dao.ProjectDAO;
import com.sasd13.proadmin.dao.ReportDAO;
import com.sasd13.proadmin.dao.RunningDAO;
import com.sasd13.proadmin.dao.RunningTeamDAO;
import com.sasd13.proadmin.dao.StudentDAO;
import com.sasd13.proadmin.dao.StudentTeamDAO;
import com.sasd13.proadmin.dao.TeacherDAO;
import com.sasd13.proadmin.dao.TeamDAO;
import com.sasd13.proadmin.util.Parameter;

public class WhereClauseParser {
	
	private static final String OPERATOR_AND = "AND";
	private static final String OPERATOR_OR = "OR";
	
	public static String parse(Class<? extends IEntityDAO<?>> mClass, Map<String, String[]> parameters) throws WhereClauseException {
		StringBuilder builder = new StringBuilder();
		
		boolean firstKey = true, firstValue = true;
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			if (firstKey) {
				firstKey = false;
			} else {
				builder.append(" " + OPERATOR_AND + " ");
				firstValue = true;
			}
			
			builder.append("(");
			
			for (String value : entry.getValue()) {
				if (firstValue) {
					firstValue = false;
				} else {
					builder.append(" " + OPERATOR_OR + " ");
				}
				
				if (TeacherDAO.class.equals(mClass)) {
					builder.append(fromTeacher(entry.getKey(), value));
				} else if (ProjectDAO.class.equals(mClass)) {
					builder.append(fromProject(entry.getKey(), value));
				} else if (StudentDAO.class.equals(mClass)) {
					builder.append(fromStudent(entry.getKey(), value));
				} else if (TeamDAO.class.equals(mClass)) {
					builder.append(fromTeam(entry.getKey(), value));
				} else if (StudentTeamDAO.class.equals(mClass)) {
					builder.append(fromStudentTeam(entry.getKey(), value));
				} else if (RunningDAO.class.equals(mClass)) {
					builder.append(fromRunning(entry.getKey(), value));
				} else if (RunningTeamDAO.class.equals(mClass)) {
					builder.append(fromRunningTeam(entry.getKey(), value));
				} else if (ReportDAO.class.equals(mClass)) {
					builder.append(fromReport(entry.getKey(), value));
				} else if (LeadEvaluationDAO.class.equals(mClass)) {
					builder.append(fromLeadEvaluation(entry.getKey(), value));
				} else if (IndividualEvaluationDAO.class.equals(mClass)) {
					builder.append(fromIndividualEvaluation(entry.getKey(), value));
				} else {
					throw new WhereClauseException("Class '" + mClass.getName() + ")' has no where clause parser");
				}
			}
			
			builder.append(")");
		}
		
		return builder.toString();
	}
	
	private static String fromTeacher(String key, String value) throws WhereClauseException {
		if (Parameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return TeacherDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new WhereClauseException("Teacher key '" + key + "' parameter parsing error");
			}
		} else if (Parameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return TeacherDAO.COLUMN_NUMBER + " = '" + value + "'";
		} else if (Parameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return TeacherDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (Parameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return TeacherDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (Parameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return TeacherDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new WhereClauseException("Teacher key '" + key + "' is not a declared parameter");
		}
	}
	
	private static String fromProject(String key, String value) throws WhereClauseException {
		if (Parameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return ProjectDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new WhereClauseException("Project key '" + key + "' parameter parsing error");
			}
		} else if (Parameter.CODE.getName().equalsIgnoreCase(key)) {
			return ProjectDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (Parameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return ProjectDAO.COLUMN_ACADEMICLEVEL + " = '" + value + "'";
		} else if (Parameter.TITLE.getName().equalsIgnoreCase(key)) {
			return ProjectDAO.COLUMN_TITLE + " = '" + value + "'";
		} else {
			throw new WhereClauseException("Project key '" + key + "' is not a declared parameter");
		}
	}
	
	private static String fromStudent(String key, String value) throws WhereClauseException {
		if (Parameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return StudentDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new WhereClauseException("Student key '" + key + "' parameter parsing error");
			}
		} else if (Parameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_NUMBER + " = '" + value + "'";
		} else if (Parameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_ACADEMICLEVEL + " = '" + value + "'";
		} else if (Parameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (Parameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (Parameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new WhereClauseException("Student key '" + key + "' is not a declared parameter");
		}
	}
	
	private static String fromTeam(String key, String value) throws WhereClauseException {
		if (Parameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return TeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new WhereClauseException("Team key '" + key + "' parameter parsing error");
			}
		} else if (Parameter.CODE.getName().equalsIgnoreCase(key)) {
			return TeamDAO.COLUMN_CODE + " = '" + value + "'";
		} else {
			throw new WhereClauseException("Team key '" + key + "' is not a declared parameter");
		}
	}
	
	private static String fromStudentTeam(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return StudentTeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.TEAM.getName().equalsIgnoreCase(key)) {
				return StudentTeamDAO.COLUMN_TEAM_ID + " = " + Long.parseLong(value);
			} else if (Parameter.STUDENT.getName().equalsIgnoreCase(key)) {
				return StudentTeamDAO.COLUMN_STUDENT_ID + " = " + Long.parseLong(value);
			} else {
				throw new WhereClauseException("StudentTeam key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new WhereClauseException("StudentTeam key '" + key + "' parameter parsing error");
		}
	}
	
	private static String fromRunning(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.YEAR.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_YEAR + " = " + Integer.parseInt(value);
			} else if (Parameter.TEACHER.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_TEACHER_ID + " = " + Long.parseLong(value);
			} else if (Parameter.PROJECT.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_PROJECT_ID + " = " + Long.parseLong(value);
			} else {
				throw new WhereClauseException("Running key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new WhereClauseException("Running key '" + key + "' parameter parsing error");
		}
	}
	
	private static String fromRunningTeam(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return RunningTeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.RUNNING.getName().equalsIgnoreCase(key)) {
				return RunningTeamDAO.COLUMN_RUNNING_ID + " = " + Long.parseLong(value);
			} else if (Parameter.TEAM.getName().equalsIgnoreCase(key)) {
				return RunningTeamDAO.COLUMN_TEAM_ID + " = " + Long.parseLong(value);
			} else {
				throw new WhereClauseException("RunningTeam key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new WhereClauseException("RunningTeam key '" + key + "' parameter parsing error");
		}
	}
	
	private static String fromReport(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return ReportDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.WEEK.getName().equalsIgnoreCase(key)) {
				return ReportDAO.COLUMN_WEEK + " = " + Integer.parseInt(value);
			} else if (Parameter.RUNNINGTEAM.getName().equalsIgnoreCase(key)) {
				return ReportDAO.COLUMN_RUNNINGTEAM + " = " + Long.parseLong(value);
			} else {
				throw new WhereClauseException("Report key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new WhereClauseException("Report key '" + key + "' parameter parsing error");
		}
	}
	
	private static String fromLeadEvaluation(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return LeadEvaluationDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.REPORT.getName().equalsIgnoreCase(key)) {
				return LeadEvaluationDAO.COLUMN_REPORT_ID + " = " + Long.parseLong(value);
			} else if (Parameter.STUDENT.getName().equalsIgnoreCase(key)) {
				return LeadEvaluationDAO.COLUMN_STUDENT_ID + " = " + Long.parseLong(value);
			} else {
				throw new WhereClauseException("LeadEvaluation key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new WhereClauseException("LeadEvaluation key '" + key + "' parameter parsing error");
		}
	}
	
	private static String fromIndividualEvaluation(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return IndividualEvaluationDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.REPORT.getName().equalsIgnoreCase(key)) {
				return IndividualEvaluationDAO.COLUMN_REPORT_ID + " = " + Long.parseLong(value);
			} else if (Parameter.STUDENT.getName().equalsIgnoreCase(key)) {
				return IndividualEvaluationDAO.COLUMN_STUDENT_ID + " = " + Long.parseLong(value);
			} else {
				throw new WhereClauseException("IndividualEvaluation key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new WhereClauseException("IndividualEvaluation key '" + key + "' parameter parsing error");
		}
	}
}
