package com.sasd13.proadmin.aaa.ws;

import com.sasd13.javaex.parser.ParserException;
import com.sasd13.proadmin.aaa.bean.AAAException;
import com.sasd13.proadmin.util.ws.EnumAAAError;

public class AAAErrorFactory {

	public static EnumAAAError make(Exception e) {
		if (ParserException.class.isAssignableFrom(e.getClass())) {
			return EnumAAAError.ERROR_PARSING_CREDENTIAL;
		} else if (AAAException.class.isAssignableFrom(e.getClass())) {
			return EnumAAAError.ERROR_TECHNICAL;
		} else {
			return EnumAAAError.UNKNOWN;
		}
	}
}
