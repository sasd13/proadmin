package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.ProjectDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;
import com.sasd13.proadmin.util.Parameter;

public class ProjectExpression implements IExpression {
	
	public static final ProjectExpression INSTANCE = new ProjectExpression();
	
	private ProjectExpression() {}
	
	@Override
	public String build(String key, String value) throws ConditionException {
		if (Parameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return ProjectDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("Project key '" + key + "' parameter parsing error");
			}
		} else if (Parameter.CODE.getName().equalsIgnoreCase(key)) {
			return ProjectDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (Parameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return ProjectDAO.COLUMN_ACADEMICLEVEL + " = '" + value + "'";
		} else if (Parameter.TITLE.getName().equalsIgnoreCase(key)) {
			return ProjectDAO.COLUMN_TITLE + " = '" + value + "'";
		} else {
			throw new ConditionException("Project key '" + key + "' is not a declared parameter");
		}
	}
}
