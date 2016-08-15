package com.sasd13.proadmin.ws.rest.handler;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.http.EnumHttpHeaderField;
import com.sasd13.javaex.net.http.EnumHttpHeaderValue;
import com.sasd13.javaex.net.http.HttpBody;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.EnumMIMEType;
import com.sasd13.proadmin.util.ws.EnumWSCode;

public class RESTHandler {
	
	public static <T> List<T> readAndParseDataFromRequest(HttpServletRequest req) throws IOException, ParserException {
		EnumMIMEType mimeType = EnumMIMEType.find(req.getContentType());
		String data = Stream.readAndClose(req.getReader());
		
		HttpBody httpBody = ParserFactory.make(mimeType).fromString(data, HttpBody.class);
		
		return ;
	}
	
	public static <T> void parseAndWriteDataToResponse(HttpServletRequest req, HttpServletResponse resp, EnumWSCode wsCode, List<T> data) throws IOException, ParserException {
		String contentType = getRequestAcceptMediaType(req);
		
		resp.setContentType(contentType);
		setResponseHeaderAccept(resp);
		
		EnumMIMEType mimeType = EnumMIMEType.find(contentType);
		HttpBody<T> httpBody = new HttpBody<T>(wsCode.getCode());
		httpBody.getContents().addAll(data);
		
		Stream.writeAndClose(resp.getWriter(), ParserFactory.make(mimeType).toString(httpBody));
	}
	
	private static String getRequestAcceptMediaType(HttpServletRequest req) {
		Enumeration<String> headerAccepts = req.getHeaders(EnumHttpHeaderField.ACCEPT.getName());
		
		String contentType = EnumMIMEType.TEXT_PLAIN.toString(), headerAccept;
		
		while (headerAccepts.hasMoreElements()) {
			headerAccept = headerAccepts.nextElement();
			
			if (EnumMIMEType.APPLICATION_JSON.toString().equals(headerAccept) || EnumMIMEType.APPLICATION_XML.toString().equals(headerAccept)) {
				contentType = headerAccept;
				
				break;
			}
		}
		
		return contentType;
	}
	
	private static void setResponseHeaderAccept(HttpServletResponse resp) {
		resp.addHeader(EnumHttpHeaderField.ACCEPT.getName(), EnumMIMEType.APPLICATION_JSON.toString());
		resp.addHeader(EnumHttpHeaderField.ACCEPT.getName(), EnumMIMEType.APPLICATION_XML.toString());
		resp.addHeader(EnumHttpHeaderField.ACCEPT.getName(), EnumMIMEType.TEXT_PLAIN.toString());
	}
}
