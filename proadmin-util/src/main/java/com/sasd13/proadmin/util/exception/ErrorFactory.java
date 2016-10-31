package com.sasd13.proadmin.util.exception;

import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.validator.ValidatorException;

public class ErrorFactory {

	public static EnumError make(Exception e) {
		if (AAAException.class.isAssignableFrom(e.getClass())) {
			return EnumError.AAA;
		} else if (ParserException.class.isAssignableFrom(e.getClass())) {
			return EnumError.PARSING_DATA;
		} else if (ValidatorException.class.isAssignableFrom(e.getClass())) {
			return EnumError.VALIDATING_DATA;
		} else if (ServiceException.class.isAssignableFrom(e.getClass())) {
			return EnumError.SERVICE;
		} else if (WSException.class.isAssignableFrom(e.getClass())) {
			return EnumError.WEBSERVICE;
		} else {
			return EnumError.UNKNOWN;
		}
	}
}
