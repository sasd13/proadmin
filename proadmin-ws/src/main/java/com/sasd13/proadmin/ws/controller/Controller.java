/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.parser.IParser;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.ws.util.Names;

/**
 *
 * @author Samir
 */
public abstract class Controller extends HttpServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	protected static final String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.WS_RESPONSE_HEADER_CONTENT_TYPE);

	protected <T> T readFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException {
		IParser parser = ParserFactory.make(req.getContentType());
		String message = Stream.read(req.getReader());

		return parser.fromString(message, mClass);
	}

	protected void writeToResponse(HttpServletResponse resp, String message) throws IOException {
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), message);
	}

	protected void handleError(HttpServletResponse resp, Logger logger, Exception e) throws IOException {
		logger.error(e);
		resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
	}
}
