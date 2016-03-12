package com.sasd13.proadmin.ws.rest.handler;

import java.util.List;
import java.util.Map;

import com.sasd13.javaex.db.Persistence;
import com.sasd13.javaex.net.http.HttpHeader;

public class ReadHandler {
	
	public static <T> T read(long id, Class<T> mClass, Persistence persistence, String headerDataRetrieve) {
		return (HttpHeader.DATA_RETRIEVE_VALUE_DEEP.getName().equals(headerDataRetrieve)) 
				? persistence.deepRead(id, mClass) 
				: persistence.read(id, mClass);
	}
	
	public static <T> List<T> read(Map<String, String[]> parameters, Class<T> mClass, Persistence persistence, String headerDataRetrieve) {
		return (HttpHeader.DATA_RETRIEVE_VALUE_DEEP.getName().equals(headerDataRetrieve)) 
				? persistence.deepRead(parameters, mClass) 
				: persistence.read(parameters, mClass);
	}
	
	public static <T> List<T> readAll(Class<T> mClass, Persistence persistence, String headerDataRetrieve) {
		return (HttpHeader.DATA_RETRIEVE_VALUE_DEEP.getName().equals(headerDataRetrieve)) 
				? persistence.deepReadAll(mClass) 
				: persistence.readAll(mClass);
	}
}
