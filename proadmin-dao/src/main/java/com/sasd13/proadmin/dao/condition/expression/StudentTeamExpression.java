package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.StudentTeamDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;
import com.sasd13.proadmin.util.Parameter;

public class StudentTeamExpression implements IExpression {
	
	public static final StudentTeamExpression INSTANCE = new StudentTeamExpression();
	
	private StudentTeamExpression() {}
	
	@Override
	public String build(String key, String value) throws ConditionException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return StudentTeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.TEAM.getName().equalsIgnoreCase(key)) {
				return StudentTeamDAO.COLUMN_TEAM_ID + " = " + Long.parseLong(value);
			} else if (Parameter.STUDENT.getName().equalsIgnoreCase(key)) {
				return StudentTeamDAO.COLUMN_STUDENT_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionException("StudentTeam key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionException("StudentTeam key '" + key + "' parameter parsing error");
		}
	}
}
