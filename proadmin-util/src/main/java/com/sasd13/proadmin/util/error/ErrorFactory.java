package com.sasd13.proadmin.util.error;

import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.util.condition.ConditionException;

public class ErrorFactory {

	public static EnumError make(Exception e) {
		if (ParserException.class.isAssignableFrom(e.getClass())) {
			return EnumError.PARSER;
		} else if (ConditionException.class.isAssignableFrom(e.getClass())) {
			return EnumError.PARAMETERS;
		} else if (RuntimeException.class.isAssignableFrom(e.getClass())) {
			return EnumError.SERVICE;
		} else {
			return EnumError.UNKNOWN;
		}
	}
}
