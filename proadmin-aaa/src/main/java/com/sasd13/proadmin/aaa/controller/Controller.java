/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.javaex.i18n.TranslationBundle;
import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.parser.IParser;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.aaa.util.Names;
import com.sasd13.proadmin.util.EnumError;
import com.sasd13.proadmin.util.ErrorFactory;

/**
 *
 * @author Samir
 */
public abstract class Controller extends HttpServlet {

	private static final long serialVersionUID = -5449641076971430518L;

	protected static final String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.AAA_RESPONSE_HEADER_CONTENT_TYPE);

	private TranslationBundle bundle;

	@Override
	public void init() throws ServletException {
		super.init();

		bundle = new TranslationBundle(Locale.ENGLISH);
	}

	protected <T> T readFromRequest(HttpServletRequest req, Class<T> mClass) throws IOException {
		IParser parser = ParserFactory.make(req.getContentType());
		String message = Stream.read(req.getReader());

		return parser.fromString(message, mClass);
	}

	protected void writeToResponse(HttpServletResponse resp, Logger logger, String message) throws IOException {
		logger.info("Message sent by AAA : " + message);
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), message);
	}

	protected void handleError(HttpServletResponse resp, Logger logger, Exception e) throws IOException {
		logger.error(e);
		writeError(resp, logger, HttpURLConnection.HTTP_INTERNAL_ERROR, ErrorFactory.make(e));
	}

	protected void writeError(HttpServletResponse resp, Logger logger, int httpStatus, EnumError error) throws IOException {
		logger.info("Error sent by AAA : code=" + error.getCode());
		resp.setStatus(httpStatus);
		resp.addHeader("Response-Errors", String.valueOf(error.getCode()));
		writeToResponse(resp, logger, bundle.getString(error.getBundleKey()));
	}
}