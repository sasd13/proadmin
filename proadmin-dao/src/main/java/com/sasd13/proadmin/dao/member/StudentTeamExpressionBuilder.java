package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentTeamExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return IStudentTeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
				return IStudentTeamDAO.COLUMN_TEAM_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
				return IStudentTeamDAO.COLUMN_STUDENT_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionException("StudentTeam key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionException("StudentTeam key '" + key + "' parameter parsing error");
		}
	}
}
