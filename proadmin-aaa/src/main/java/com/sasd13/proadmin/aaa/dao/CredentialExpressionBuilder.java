package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.IExpressionBuilder;

public class CredentialExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (ICredentialDAO.COLUMN_USERNAME.equalsIgnoreCase(key)) {
			return ICredentialDAO.COLUMN_USERNAME + " = '" + value + "'";
		} else if (ICredentialDAO.COLUMN_PASSWORD.equalsIgnoreCase(key)) {
			return ICredentialDAO.COLUMN_PASSWORD + " = '" + value + "'";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}
