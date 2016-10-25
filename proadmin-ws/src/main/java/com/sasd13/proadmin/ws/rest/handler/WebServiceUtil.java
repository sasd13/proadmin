package com.sasd13.proadmin.ws.rest.handler;

import java.io.BufferedReader;
import java.io.IOException;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;

public class WebServiceUtil {

	public static <T> T[] readArrayDataFromRequest(BufferedReader reader, String mimeType, Class<T> mClass) throws ParserException, IOException {
		return ParserFactory.make(mimeType).fromStringArray(Stream.readAndClose(reader), mClass);
	}
}
