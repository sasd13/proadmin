package com.sasd13.proadmin.ws.rest.handler;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.http.EnumHttpHeaderField;
import com.sasd13.javaex.net.http.EnumHttpHeaderValue;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.EnumMediaType;

public class RESTHandler {
	
	public static Object readAndParseDataFromRequest(HttpServletRequest req, Class<?> mClass, boolean isCollection) throws IOException, ParserException {
		EnumMediaType mediaType = EnumMediaType.find(req.getContentType());
		String data = Stream.readAndClose(req.getReader());
		
		if (isCollection) {
			return ParserFactory.make(mediaType).fromStringArray(data, mClass);
		} else {
			return ParserFactory.make(mediaType).fromString(data, mClass);
		}
	}
	
	public static void parseAndWriteDataToResponse(HttpServletRequest req, HttpServletResponse resp, Object respData, boolean isCollection) throws IOException, ParserException {
		String contentType = getRequestAcceptMediaType(req);
		
		resp.setContentType(contentType);
		setResponseHeaderAccept(resp);
		setResponseHeaderDataCollection(resp, isCollection);
		
		EnumMediaType mediaType = EnumMediaType.find(contentType);
		String sRespData = ParserFactory.make(mediaType).toString(respData);
		
		Stream.writeAndClose(resp.getWriter(), sRespData);
	}
	
	private static String getRequestAcceptMediaType(HttpServletRequest req) {
		Enumeration<String> headerAccepts = req.getHeaders(EnumHttpHeaderField.ACCEPT.getName());
		
		String contentType = EnumMediaType.TEXT_PLAIN.getMIMEType(), headerAccept;
		
		while (headerAccepts.hasMoreElements()) {
			headerAccept = headerAccepts.nextElement();
			
			if (EnumMediaType.APPLICATION_JSON.getMIMEType().equals(headerAccept) || EnumMediaType.APPLICATION_XML.getMIMEType().equals(headerAccept)) {
				contentType = headerAccept;
				
				break;
			}
		}
		
		return contentType;
	}
	
	private static void setResponseHeaderAccept(HttpServletResponse resp) {
		resp.addHeader(EnumHttpHeaderField.ACCEPT.getName(), EnumMediaType.APPLICATION_JSON.getMIMEType());
		resp.addHeader(EnumHttpHeaderField.ACCEPT.getName(), EnumMediaType.APPLICATION_XML.getMIMEType());
		resp.addHeader(EnumHttpHeaderField.ACCEPT.getName(), EnumMediaType.TEXT_PLAIN.getMIMEType());
	}
	
	private static void setResponseHeaderDataCollection(HttpServletResponse resp, boolean isCollection) {
		resp.addHeader(
				EnumHttpHeaderField.DATA_COLLECTION.getName(), 
				isCollection ? EnumHttpHeaderValue.DATA_COLLECTION_YES.getName() : EnumHttpHeaderValue.DATA_COLLECTION_NO.getName());
	}
}
