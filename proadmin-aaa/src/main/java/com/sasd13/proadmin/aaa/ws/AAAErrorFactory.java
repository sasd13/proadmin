package com.sasd13.proadmin.aaa.ws;

import com.sasd13.javaex.parser.ParserException;
import com.sasd13.proadmin.aaa.AAAException;
import com.sasd13.proadmin.util.net.EnumAAAError;

public class AAAErrorFactory {

	public static EnumAAAError make(Exception e) {
		if (ParserException.class.isAssignableFrom(e.getClass())) {
			return EnumAAAError.ERROR_PARSING_CREDENTIAL;
		} else if (AAAException.class.isAssignableFrom(e.getClass())) {
			return EnumAAAError.ERROR_SERVICE;
		} else {
			return EnumAAAError.UNKNOWN;
		}
	}
}
