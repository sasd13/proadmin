/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet.rest;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.javaex.i18n.TranslationBundle;
import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.ICredentialReadService;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.proadmin.aaa.service.CredentialReadService;
import com.sasd13.proadmin.aaa.util.Names;
import com.sasd13.proadmin.aaa.util.SessionBuilder;
import com.sasd13.proadmin.aaa.validator.CredentialValidator;
import com.sasd13.proadmin.util.exception.EnumError;
import com.sasd13.proadmin.util.exception.ErrorFactory;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LogInServlet extends HttpServlet {

	private static final long serialVersionUID = 4147483186176202467L;

	private static final Logger LOG = Logger.getLogger(LogInServlet.class);
	private static final String PARAMETER_USERNAME = AppProperties.getProperty(Names.AAA_REQUEST_LOGIN_PARAMETER_USERNAME);
	private static final String PARAMETER_PASSWORD = AppProperties.getProperty(Names.AAA_REQUEST_LOGIN_PARAMETER_PASSWORD);
	private static final String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.AAA_RESPONSE_CONTENT_TYPE);

	private TranslationBundle bundle;
	private IValidator<Credential> validator;
	private ICredentialReadService credentialReadService;

	@Override
	public void init() throws ServletException {
		super.init();

		bundle = new TranslationBundle(Locale.ENGLISH);
		validator = new CredentialValidator();
		credentialReadService = new CredentialReadService();
	}

	@SuppressWarnings("unchecked")
	private Credential readFromRequest(HttpServletRequest req) throws ParserException, IOException {
		Map<String, String> map = (Map<String, String>) ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), Map.class);

		if (!map.containsKey(PARAMETER_USERNAME) || !map.containsKey(PARAMETER_PASSWORD)) {
			throw new ParserException("Credential username/password not send");
		}

		return new Credential(map.get(PARAMETER_USERNAME), map.get(PARAMETER_PASSWORD));
	}

	private void writeToResponse(HttpServletResponse resp, String message) throws IOException {
		LOG.info("Message send by AAA : " + message);
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.write(resp.getWriter(), message);
	}

	private void writeError(HttpServletResponse resp, EnumError error) throws IOException {
		LOG.info("Error send by AAA : code=" + error.getCode());
		resp.setHeader(EnumHttpHeader.RESPONSE_ERROR.getName(), String.valueOf(error.getCode()));
	}

	private void doCatch(Exception e, String logMessage, HttpServletResponse resp) throws IOException {
		LOG.error(logMessage);

		EnumError error = ErrorFactory.make(e);

		writeError(resp, error);
		writeToResponse(resp, bundle.getString(error.getBundleKey()));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("doPost");

		try {
			Credential credential = readFromRequest(req);

			validator.validate(credential);

			if (credentialReadService.contains(credential)) {
				String message = ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(SessionBuilder.build(credential));

				writeToResponse(resp, message);
			} else {
				writeError(resp, EnumError.AAA_LOGIN_FAILED);
			}
		} catch (Exception e) {
			doCatch(e, "doPost failed. " + e.getMessage(), resp);
		}
	}
}
