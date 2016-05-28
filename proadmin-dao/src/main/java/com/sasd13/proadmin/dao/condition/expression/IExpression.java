package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.condition.ConditionException;

public interface IExpression {
	
	String build(String key, String value) throws ConditionException;
}
