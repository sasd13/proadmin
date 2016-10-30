/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
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
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.aaa.service.CredentialManageService;
import com.sasd13.proadmin.aaa.util.Names;
import com.sasd13.proadmin.aaa.validator.CredentialValidator;
import com.sasd13.proadmin.util.ws.EnumError;

/**
 *
 * @author Samir
 */
@WebServlet("/sign")
public class SignServlet extends HttpServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final Logger LOG = Logger.getLogger(SignServlet.class);
	private static final String PARAMETER_USERNAME = AppProperties.getProperty(Names.AAA_REQUEST_LOGIN_PARAMETER_USERNAME);
	private static final String PARAMETER_PASSWORD = AppProperties.getProperty(Names.AAA_REQUEST_LOGIN_PARAMETER_PASSWORD);
	private static final String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.AAA_RESPONSE_CONTENT_TYPE);

	private TranslationBundle bundle;
	private IValidator<Credential> validator;
	private IManageService<Credential> manageService;

	@Override
	public void init() throws ServletException {
		super.init();

		bundle = new TranslationBundle(Locale.ENGLISH);
		validator = new CredentialValidator();
		manageService = new CredentialManageService();
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
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.writeAndClose(resp.getWriter(), message);
	}

	private void doCatch(String logMessage, HttpServletResponse resp, EnumError error) throws IOException {
		LOG.error(logMessage);
		resp.setHeader(EnumHttpHeader.WS_ERROR.getName(), String.valueOf(error.getCode()));
		writeToResponse(resp, bundle.getString(error.getBundleKey()));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("doPost");

		try {
			Credential credential = readFromRequest(req);

			validator.validate(credential);
			manageService.create(credential);
		} catch (ParserException e) {
			doCatch("doPost failed. " + e.getMessage(), resp, EnumError.DATA_PARSING);
		} catch (ValidatorException e) {
			doCatch("doPost failed. " + e.getMessage(), resp, EnumError.DATA_VALIDATING);
		} catch (ServiceException e) {
			doCatch("doPost failed. " + e.getMessage(), resp, EnumError.SERVICE);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("doPut");

		try {
			Credential credential = readFromRequest(req);

			validator.validate(credential);
			manageService.update(credential);
		} catch (ParserException e) {
			doCatch("doPut failed. " + e.getMessage(), resp, EnumError.DATA_PARSING);
		} catch (ValidatorException e) {
			doCatch("doPut failed. " + e.getMessage(), resp, EnumError.DATA_VALIDATING);
		} catch (ServiceException e) {
			doCatch("doPut failed. " + e.getMessage(), resp, EnumError.SERVICE);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("doDelete");

		try {
			Credential credential = readFromRequest(req);

			validator.validate(credential);
			manageService.delete(credential);
		} catch (ParserException e) {
			doCatch("doDelete failed. " + e.getMessage(), resp, EnumError.DATA_PARSING);
		} catch (ValidatorException e) {
			doCatch("doDelete failed. " + e.getMessage(), resp, EnumError.DATA_VALIDATING);
		} catch (ServiceException e) {
			doCatch("doDelete failed. " + e.getMessage(), resp, EnumError.SERVICE);
		}
	}
}
