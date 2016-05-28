package com.sasd13.proadmin.dao.condition;

import java.util.Map;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.dao.condition.expression.ExpressionFactory;
import com.sasd13.proadmin.dao.condition.expression.IExpression;

public class ConditionParser {
	
	private static final String OPERATOR_AND = "AND";
	private static final String OPERATOR_OR = "OR";
	
	public static String parse(Map<String, String[]> parameters, Class<? extends IEntityDAO<?>> mClass) throws ConditionException {
		StringBuilder builder = new StringBuilder();
		
		IExpression expression = ExpressionFactory.make(mClass);
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
				
				builder.append(expression.build(entry.getKey(), value));
			}
			
			builder.append(")");
		}
		
		return builder.toString();
	}
}
