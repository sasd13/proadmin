package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentTeamExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return IStudentTeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("StudentTeam key '" + key + "' parsing error");
			}
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return IStudentTeamDAO.COLUMN_STUDENT_CODE + " = " + value;
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IStudentTeamDAO.COLUMN_TEAM_CODE + " = " + value;
		} else {
			throw new ConditionException("StudentTeam key '" + key + "' is not a declared parameter");
		}
	}
}
