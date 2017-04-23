package com.sasd13.proadmin.ws.util;

import com.sasd13.javaex.conf.AppProperties;

public interface Constants {

	String REQ_ATTR_DAO = "REQ_ATTR_DAO";
	String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.WS_RESPONSE_HEADER_CONTENT_TYPE);
	String PATTERN_DATE_FORMAT = AppProperties.getProperty(Names.WS_PATTERN_DATE_FORMAT);
}
