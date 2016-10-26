package com.sasd13.proadmin.ws.rest;

import com.sasd13.javaex.parser.ParserException;
import com.sasd13.proadmin.util.net.EnumWSError;
import com.sasd13.proadmin.ws.WSException;

public class WSErrorFactory {

	public static EnumWSError make(Exception e) {
		if (ParserException.class.isAssignableFrom(e.getClass())) {
			return EnumWSError.ERROR_PARSING_DATA;
		} else if (WSException.class.isAssignableFrom(e.getClass())) {
			return EnumWSError.ERROR_TECHNICAL;
		} else {
			return EnumWSError.UNKNOWN;
		}
	}
}
