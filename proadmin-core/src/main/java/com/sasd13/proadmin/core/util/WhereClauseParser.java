package com.sasd13.proadmin.core.util;

import java.util.Map;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.core.bean.running.LeadEvaluation;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.StudentTeam;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.IndividualEvaluationDAO;
import com.sasd13.proadmin.core.db.LeadEvaluationDAO;
import com.sasd13.proadmin.core.db.ProjectDAO;
import com.sasd13.proadmin.core.db.ReportDAO;
import com.sasd13.proadmin.core.db.RunningDAO;
import com.sasd13.proadmin.core.db.StudentDAO;
import com.sasd13.proadmin.core.db.StudentTeamDAO;
import com.sasd13.proadmin.core.db.TeacherDAO;
import com.sasd13.proadmin.core.db.TeamDAO;

public class WhereClauseParser {
	
	private static final String OPERATOR_AND = "AND";
	private static final String OPERATOR_OR = "OR";
	
	public static String parse(Class<?> mClass, Map<String, String[]> parameters) throws WhereClauseException {
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
				
				if (Teacher.class.equals(mClass)) {
					builder.append(fromTeacher(entry.getKey(), value));
				} else if (Project.class.equals(mClass)) {
					builder.append(fromProject(entry.getKey(), value));
				} else if (Running.class.equals(mClass)) {
					builder.append(fromRunning(entry.getKey(), value));
				} else if (Team.class.equals(mClass)) {
					builder.append(fromTeam(entry.getKey(), value));
				} else if (Student.class.equals(mClass)) {
					builder.append(fromStudent(entry.getKey(), value));
				} else if (StudentTeam.class.equals(mClass)) {
					builder.append(fromStudentTeam(entry.getKey(), value));
				} else if (Report.class.equals(mClass)) {
					builder.append(fromReport(entry.getKey(), value));
				} else if (LeadEvaluation.class.equals(mClass)) {
					builder.append(fromLeadEvaluation(entry.getKey(), value));
				} else if (IndividualEvaluation.class.equals(mClass)) {
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
		String condition = null;
		
		if (EnumURLParameter.NUMBER.getName().equals(key)) {
			condition = TeacherDAO.COLUMN_NUMBER + " = '" + value + "'";
		} else if (EnumURLParameter.FIRSTNAME.getName().equals(key)) {
			condition = TeacherDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (EnumURLParameter.LASTNAME.getName().equals(key)) {
			condition = TeacherDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (EnumURLParameter.EMAIL.getName().equals(key)) {
			condition = TeacherDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new WhereClauseException("Teacher key '" + key + "' is not a declared parameter");
		}
		
		return condition;
	}
	
	private static String fromProject(String key, String value) throws WhereClauseException {
		String condition = null;
		
		if (EnumURLParameter.CODE.getName().equals(key)) {
			condition = ProjectDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (EnumURLParameter.ACADEMICLEVEL.getName().equals(key)) {
			condition = ProjectDAO.COLUMN_ACADEMICLEVEL + " = '" + value + "'";
		} else if (EnumURLParameter.TITLE.getName().equals(key)) {
			condition = ProjectDAO.COLUMN_TITLE + " = '" + value + "'";
		} else {
			throw new WhereClauseException("Project key '" + key + "' is not a declared parameter");
		}
		
		return condition;
	}
	
	private static String fromRunning(String key, String value) throws WhereClauseException {
		String condition = null;
		
		try {
			if (EnumURLParameter.YEAR.getName().equals(key)) {
				condition = RunningDAO.COLUMN_YEAR + " = " + Integer.parseInt(value);
			} else if (EnumURLParameter.TEACHER.getName().equals(key)) {
				condition = RunningDAO.COLUMN_TEACHER_ID + " = " + Integer.parseInt(value);
			} else if (EnumURLParameter.PROJECT.getName().equals(key)) {
				condition = RunningDAO.COLUMN_PROJECT_ID + " = " + Integer.parseInt(value);
			} else {
				throw new WhereClauseException("Running key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new WhereClauseException("Running key '" + key + "' parameter parsing error. See above");
		}
		
		return condition;
	}
	
	private static String fromTeam(String key, String value) throws WhereClauseException {
		String condition = null;
		
		if (EnumURLParameter.CODE.getName().equals(key)) {
			condition = TeamDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (EnumURLParameter.RUNNING.getName().equals(key)) {
			try {
				condition = TeamDAO.COLUMN_RUNNING_ID + " = " + Integer.parseInt(value);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new WhereClauseException("Team key '" + key + "' parameter parsing error. See above");
			}
		} else {
			throw new WhereClauseException("Team key '" + key + "' is not a declared parameter");
		}
		
		return condition;
	}
	
	private static String fromStudent(String key, String value) throws WhereClauseException {
		String condition = null;
		
		if (EnumURLParameter.NUMBER.getName().equals(key)) {
			condition = StudentDAO.COLUMN_NUMBER + " = '" + value + "'";
		} else if (EnumURLParameter.ACADEMICLEVEL.getName().equals(key)) {
			condition = StudentDAO.COLUMN_ACADEMICLEVEL + " = '" + value + "'";
		} else if (EnumURLParameter.FIRSTNAME.getName().equals(key)) {
			condition = StudentDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (EnumURLParameter.LASTNAME.getName().equals(key)) {
			condition = StudentDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (EnumURLParameter.EMAIL.getName().equals(key)) {
			condition = StudentDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new WhereClauseException("Student key '" + key + "' is not a declared parameter");
		}
		
		return condition;
	}
	
	private static String fromStudentTeam(String key, String value) throws WhereClauseException {
		String condition = null;
		
		try {
			if (EnumURLParameter.TEAM.getName().equals(key)) {
				condition = StudentTeamDAO.COLUMN_TEAM_ID + " = " + Integer.parseInt(value);
			} else if (EnumURLParameter.STUDENT.getName().equals(key)) {
				condition = StudentTeamDAO.COLUMN_STUDENT_ID + " = " + Integer.parseInt(value);
			} else {
				throw new WhereClauseException("StudentTeam key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new WhereClauseException("StudentTeam key '" + key + "' parameter parsing error. See above");
		}
		
		return condition;
	}
	
	private static String fromReport(String key, String value) throws WhereClauseException {
		String condition = null;
		
		try {
			if (EnumURLParameter.WEEK.getName().equals(key)) {
				condition = ReportDAO.COLUMN_WEEK + " = " + Integer.parseInt(value);
			} else if (EnumURLParameter.TEAM.getName().equals(key)) {
				condition = ReportDAO.COLUMN_TEAM_ID + " = " + Integer.parseInt(value);
			} else {
				throw new WhereClauseException("Report key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new WhereClauseException("Report key '" + key + "' parameter parsing error. See above");
		}
		
		return condition;
	}
	
	private static String fromLeadEvaluation(String key, String value) throws WhereClauseException {
		String condition = null;
		
		try {
			if (EnumURLParameter.REPORT.getName().equals(key)) {
				condition = LeadEvaluationDAO.COLUMN_REPORT_ID + " = " + Integer.parseInt(value);
			} else if (EnumURLParameter.STUDENT.getName().equals(key)) {
				condition = LeadEvaluationDAO.COLUMN_STUDENT_ID + " = " + Integer.parseInt(value);
			} else {
				throw new WhereClauseException("LeadEvaluation key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new WhereClauseException("LeadEvaluation key '" + key + "' parameter parsing error. See above");
		}
		
		return condition;
	}
	
	private static String fromIndividualEvaluation(String key, String value) throws WhereClauseException {
		String condition = null;
		
		try {
			if (EnumURLParameter.REPORT.getName().equals(key)) {
				condition = IndividualEvaluationDAO.COLUMN_REPORT_ID + " = " + Integer.parseInt(value);
			} else if (EnumURLParameter.STUDENT.getName().equals(key)) {
				condition = IndividualEvaluationDAO.COLUMN_STUDENT_ID + " = " + Integer.parseInt(value);
			} else {
				throw new WhereClauseException("IndividualEvaluation key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new WhereClauseException("IndividualEvaluation key '" + key + "' parameter parsing error. See above");
		}
		
		return condition;
	}
}
