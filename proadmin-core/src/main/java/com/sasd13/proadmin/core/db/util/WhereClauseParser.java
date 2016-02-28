package com.sasd13.proadmin.core.db.util;

import java.util.Map;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.core.db.IndividualEvaluationDAO;
import com.sasd13.proadmin.core.db.LeadEvaluationDAO;
import com.sasd13.proadmin.core.db.ProjectDAO;
import com.sasd13.proadmin.core.db.ReportDAO;
import com.sasd13.proadmin.core.db.RunningDAO;
import com.sasd13.proadmin.core.db.StudentDAO;
import com.sasd13.proadmin.core.db.StudentTeamDAO;
import com.sasd13.proadmin.core.db.TeacherDAO;
import com.sasd13.proadmin.core.db.TeamDAO;
import com.sasd13.proadmin.core.util.Parameter;

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
				} else if (RunningDAO.class.equals(mClass)) {
					builder.append(fromRunning(entry.getKey(), value));
				} else if (TeamDAO.class.equals(mClass)) {
					builder.append(fromTeam(entry.getKey(), value));
				} else if (StudentDAO.class.equals(mClass)) {
					builder.append(fromStudent(entry.getKey(), value));
				} else if (StudentTeamDAO.class.equals(mClass)) {
					builder.append(fromStudentTeam(entry.getKey(), value));
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
		
		return builder.toString().trim();
	}
	
	private static String fromTeacher(String key, String value) throws WhereClauseException {
		if (Parameter.NUMBER.getName().equals(key)) {
			return TeacherDAO.COLUMN_NUMBER + " = '" + value + "'";
		} else if (Parameter.FIRSTNAME.getName().equals(key)) {
			return TeacherDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (Parameter.LASTNAME.getName().equals(key)) {
			return TeacherDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (Parameter.EMAIL.getName().equals(key)) {
			return TeacherDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new WhereClauseException("Teacher key '" + key + "' is not a declared parameter");
		}
	}
	
	private static String fromProject(String key, String value) throws WhereClauseException {
		if (Parameter.CODE.getName().equals(key)) {
			return ProjectDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (Parameter.ACADEMICLEVEL.getName().equals(key)) {
			return ProjectDAO.COLUMN_ACADEMICLEVEL + " = '" + value + "'";
		} else if (Parameter.TITLE.getName().equals(key)) {
			return ProjectDAO.COLUMN_TITLE + " = '" + value + "'";
		} else {
			throw new WhereClauseException("Project key '" + key + "' is not a declared parameter");
		}
	}
	
	private static String fromRunning(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.YEAR.getName().equals(key)) {
				return RunningDAO.COLUMN_YEAR + " = " + Integer.parseInt(value);
			} else if (Parameter.TEACHER.getName().equals(key)) {
				return RunningDAO.COLUMN_TEACHER_ID + " = " + Integer.parseInt(value);
			} else if (Parameter.PROJECT.getName().equals(key)) {
				return RunningDAO.COLUMN_PROJECT_ID + " = " + Integer.parseInt(value);
			} else {
				throw new WhereClauseException("Running key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new WhereClauseException("Running key '" + key + "' parameter parsing error. See above");
		}
	}
	
	private static String fromTeam(String key, String value) throws WhereClauseException {
		if (Parameter.CODE.getName().equals(key)) {
			return TeamDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (Parameter.RUNNING.getName().equals(key)) {
			try {
				return TeamDAO.COLUMN_RUNNING_ID + " = " + Integer.parseInt(value);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new WhereClauseException("Team key '" + key + "' parameter parsing error. See above");
			}
		} else {
			throw new WhereClauseException("Team key '" + key + "' is not a declared parameter");
		}
	}
	
	private static String fromStudent(String key, String value) throws WhereClauseException {
		if (Parameter.NUMBER.getName().equals(key)) {
			return StudentDAO.COLUMN_NUMBER + " = '" + value + "'";
		} else if (Parameter.ACADEMICLEVEL.getName().equals(key)) {
			return StudentDAO.COLUMN_ACADEMICLEVEL + " = '" + value + "'";
		} else if (Parameter.FIRSTNAME.getName().equals(key)) {
			return StudentDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (Parameter.LASTNAME.getName().equals(key)) {
			return StudentDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (Parameter.EMAIL.getName().equals(key)) {
			return StudentDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new WhereClauseException("Student key '" + key + "' is not a declared parameter");
		}
	}
	
	private static String fromStudentTeam(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.TEAM.getName().equals(key)) {
				return StudentTeamDAO.COLUMN_TEAM_ID + " = " + Integer.parseInt(value);
			} else if (Parameter.STUDENT.getName().equals(key)) {
				return StudentTeamDAO.COLUMN_STUDENT_ID + " = " + Integer.parseInt(value);
			} else {
				throw new WhereClauseException("StudentTeam key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new WhereClauseException("StudentTeam key '" + key + "' parameter parsing error. See above");
		}
	}
	
	private static String fromReport(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.WEEK.getName().equals(key)) {
				return ReportDAO.COLUMN_WEEK + " = " + Integer.parseInt(value);
			} else if (Parameter.TEAM.getName().equals(key)) {
				return ReportDAO.COLUMN_TEAM_ID + " = " + Integer.parseInt(value);
			} else {
				throw new WhereClauseException("Report key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new WhereClauseException("Report key '" + key + "' parameter parsing error. See above");
		}
	}
	
	private static String fromLeadEvaluation(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.REPORT.getName().equals(key)) {
				return LeadEvaluationDAO.COLUMN_REPORT_ID + " = " + Integer.parseInt(value);
			} else if (Parameter.STUDENT.getName().equals(key)) {
				return LeadEvaluationDAO.COLUMN_STUDENT_ID + " = " + Integer.parseInt(value);
			} else {
				throw new WhereClauseException("LeadEvaluation key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new WhereClauseException("LeadEvaluation key '" + key + "' parameter parsing error. See above");
		}
	}
	
	private static String fromIndividualEvaluation(String key, String value) throws WhereClauseException {
		try {
			if (Parameter.REPORT.getName().equals(key)) {
				return IndividualEvaluationDAO.COLUMN_REPORT_ID + " = " + Integer.parseInt(value);
			} else if (Parameter.STUDENT.getName().equals(key)) {
				return IndividualEvaluationDAO.COLUMN_STUDENT_ID + " = " + Integer.parseInt(value);
			} else {
				throw new WhereClauseException("IndividualEvaluation key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new WhereClauseException("IndividualEvaluation key '" + key + "' parameter parsing error. See above");
		}
	}
}
