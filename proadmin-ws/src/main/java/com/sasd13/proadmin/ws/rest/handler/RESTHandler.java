package com.sasd13.proadmin.ws.rest.handler;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.io.ContentIO;
import com.sasd13.javaex.net.http.HttpHeader;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.MediaType;

public class RESTHandler {
	
	public static <T> T readAndParseDataFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException, ParserException {
		MediaType mediaType = MediaType.find(req.getContentType());
		String data = ContentIO.readAndClose(req.getReader());
		
		return (T) ParserFactory.make(mediaType).fromString(data, mClass);
	}
	
	public static void parseAndWriteDataToResponse(HttpServletRequest req, HttpServletResponse resp, Object respData) throws IOException, ParserException {
		String contentType = getRequestAcceptMediaType(req);
		
		resp.setContentType(contentType);
		setResponseHeaderAccept(resp);
		
		MediaType mediaType = MediaType.find(contentType);
		String sRespData = ParserFactory.make(mediaType).toString(respData);
		
		ContentIO.writeAndClose(resp.getWriter(), sRespData);
	}
	
	private static String getRequestAcceptMediaType(HttpServletRequest req) {
		Enumeration<String> headerAccepts = req.getHeaders(HttpHeader.ACCEPT_FIELD.getName());
		
		String contentType = MediaType.TEXT_PLAIN.getMIMEType(), headerAccept;
		
		while (headerAccepts.hasMoreElements()) {
			headerAccept = headerAccepts.nextElement();
			
			if (MediaType.APPLICATION_JSON.getMIMEType().equals(headerAccept) || MediaType.APPLICATION_XML.getMIMEType().equals(headerAccept)) {
				contentType = headerAccept;
				
				break;
			}
		}
		
		return contentType;
	}
	
	private static void setResponseHeaderAccept(HttpServletResponse resp) {
		resp.addHeader(HttpHeader.ACCEPT_FIELD.getName(), MediaType.APPLICATION_JSON.getMIMEType());
		resp.addHeader(HttpHeader.ACCEPT_FIELD.getName(), MediaType.APPLICATION_XML.getMIMEType());
		resp.addHeader(HttpHeader.ACCEPT_FIELD.getName(), MediaType.TEXT_PLAIN.getMIMEType());
	}
}
