package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentTeamExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return IStudentTeamDAO.COLUMN_STUDENT_CODE + " = " + value;
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IStudentTeamDAO.COLUMN_TEAM_CODE + " = " + value;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
