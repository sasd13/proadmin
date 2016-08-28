package com.sasd13.proadmin.ws.rest.handler;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.http.EnumHttpHeaderField;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.EnumMIMEType;
import com.sasd13.proadmin.util.ws.EnumWSCode;

public class RESTHandler {

	public static <T> T readDataFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException, ParserException {
		return ParserFactory
				.make(EnumMIMEType.find(req.getContentType()))
				.fromString(Stream.readAndClose(req.getReader()), mClass);
	}

	public static <T> T[] readArrayDataFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException, ParserException {
		return ParserFactory
				.make(EnumMIMEType.find(req.getContentType()))
				.fromStringArray(Stream.readAndClose(req.getReader()), mClass);
	}

	public static void writeDataToResponse(HttpServletRequest req, HttpServletResponse resp, EnumWSCode wsCode, Object data) throws IOException, ParserException {
		String contentType = getRequestAcceptMediaType(req);

		resp.setContentType(contentType);
		setWSResponseCode(resp, wsCode);
		setResponseHeaderAccept(resp);

		Stream.writeAndClose(resp.getWriter(), ParserFactory.make(EnumMIMEType.find(contentType)).toString(data));
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

	private static void setWSResponseCode(HttpServletResponse resp, EnumWSCode wsCode) {
		resp.addHeader(EnumHttpHeaderField.WS_RESPONSE_CODE.getName(), String.valueOf(wsCode.getCode()));
	}
}
