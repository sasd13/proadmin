/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet.rest;

import java.io.IOException;
import java.util.List;
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
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.aaa.util.Names;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.exception.ErrorFactory;

/**
 *
 * @author Samir
 */
public abstract class AAAServlet extends HttpServlet {

	private static final long serialVersionUID = -5449641076971430518L;

	private static final Logger LOGGER = Logger.getLogger(AAAServlet.class);
	protected static final String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.AAA_RESPONSE_HEADER_CONTENT_TYPE);

	private TranslationBundle bundle;

	@Override
	public void init() throws ServletException {
		super.init();

		bundle = new TranslationBundle(Locale.ENGLISH);
	}

	protected List<Credential> readFromRequest(HttpServletRequest req) throws IOException, ParserException {
		IParser parser = ParserFactory.make(req.getContentType());
		String message = Stream.read(req.getReader());

		return (List<Credential>) parser.fromStringArray(message, Credential.class);
	}

	protected void writeToResponse(HttpServletResponse resp, String message) throws IOException {
		LOGGER.info("Message sent by AAA : " + message);
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), message);
	}

	protected void handleError(Exception e, HttpServletResponse resp) throws IOException {
		LOGGER.error(e);
		writeError(resp, ErrorFactory.make(e));
	}

	protected void writeError(HttpServletResponse resp, EnumError error) throws IOException {
		LOGGER.info("Error sent by AAA : code=" + error.getCode());
		resp.setHeader(EnumHttpHeader.RESPONSE_ERROR.getName(), String.valueOf(error.getCode()));

		String message = bundle.getString(error.getBundleKey());

		writeToResponse(resp, message);
	}
}
