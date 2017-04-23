package com.sasd13.proadmin.aaa.util;

import com.sasd13.javaex.conf.AppProperties;

public interface Constants {

	String REQ_ATTR_DAO = "REQ_ATTR_DAO";
	String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.AAA_RESPONSE_HEADER_CONTENT_TYPE);
}
