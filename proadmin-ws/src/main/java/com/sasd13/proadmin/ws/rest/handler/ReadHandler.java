package com.sasd13.proadmin.ws.rest.handler;

import java.util.List;
import java.util.Map;

import com.sasd13.javaex.db.LayeredPersistor;
import com.sasd13.javaex.net.http.EnumHttpHeaderValue;

public class ReadHandler {

	public static <T> T read(long id, Class<T> mClass, LayeredPersistor persistor, String headerDataRetrieve) {
		return EnumHttpHeaderValue.DATA_RETRIEVE_DEEP.getName().equalsIgnoreCase(headerDataRetrieve) 
				? persistor.deepRead(id, mClass) 
				: persistor.read(id, mClass);
	}

	public static <T> List<T> read(Map<String, String[]> parameters, Class<T> mClass, LayeredPersistor persistor, String headerDataRetrieve) {
		return EnumHttpHeaderValue.DATA_RETRIEVE_DEEP.getName().equalsIgnoreCase(headerDataRetrieve) 
				? persistor.deepRead(parameters, mClass) 
				: persistor.read(parameters, mClass);
	}

	public static <T> List<T> readAll(Class<T> mClass, LayeredPersistor persistor, String headerDataRetrieve) {
		return EnumHttpHeaderValue.DATA_RETRIEVE_DEEP.getName().equalsIgnoreCase(headerDataRetrieve) 
				? persistor.deepReadAll(mClass) 
				: persistor.readAll(mClass);
	}
}
