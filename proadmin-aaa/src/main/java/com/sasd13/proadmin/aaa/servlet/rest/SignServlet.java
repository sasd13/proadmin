/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet.rest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.conf.AppProperties;
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
import com.sasd13.proadmin.aaa.validator.CredentialValidator;
import com.sasd13.proadmin.util.Names;
import com.sasd13.proadmin.util.ws.EnumError;

/**
 *
 * @author Samir
 */
@WebServlet("/sign")
public class SignServlet extends HttpServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final Logger LOG = Logger.getLogger(SignServlet.class);
	private static final String PARAMETER_USERNAME = "username";
	private static final String PARAMETER_PASSWORD = "password";
	private static final String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.WS_RESPONSE_CONTENT_TYPE);

	IValidator<Credential> validator;
	IManageService<Credential> manageService;

	@Override
	public void init() throws ServletException {
		super.init();

		validator = new CredentialValidator();
		manageService = new CredentialManageService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("doPost");

		try {
			Credential credential = getCredentialFromRequest(req);

			validator.validate(credential);
			manageService.create(credential);
		} catch (ParserException e) {
			doCatch(e, "doPost failed", EnumError.DATA_PARSING, resp);
		} catch (ValidatorException e) {
			doCatch(e, "doPost failed", EnumError.DATA_VALIDATING, resp);
		} catch (ServiceException e) {
			doCatch(e, "doPost failed", EnumError.SERVICE, resp);
		}
	}

	@SuppressWarnings("unchecked")
	private Credential getCredentialFromRequest(HttpServletRequest req) throws ParserException, IOException {
		Map<String, String> map = (Map<String, String>) ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), Map.class);

		return new Credential(map.get(PARAMETER_USERNAME), map.get(PARAMETER_PASSWORD));
	}

	private void doCatch(Exception e, String logMessage, EnumError aaaError, HttpServletResponse resp) throws IOException {
		LOG.error(logMessage, e);
		resp.setHeader(EnumHttpHeader.WS_ERROR.getName(), String.valueOf(aaaError.getCode()));
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.writeAndClose(resp.getWriter(), e.getMessage());
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("doPut");

		try {
			Credential credential = getCredentialFromRequest(req);

			validator.validate(credential);
			manageService.update(credential);
		} catch (ParserException e) {
			doCatch(e, "doPut failed", EnumError.DATA_PARSING, resp);
		} catch (ValidatorException e) {
			doCatch(e, "doPut failed", EnumError.DATA_VALIDATING, resp);
		} catch (ServiceException e) {
			doCatch(e, "doPut failed", EnumError.SERVICE, resp);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("doDelete");

		try {
			Credential credential = getCredentialFromRequest(req);

			validator.validate(credential);
			manageService.delete(credential);
		} catch (ParserException e) {
			doCatch(e, "doPut failed", EnumError.DATA_PARSING, resp);
		} catch (ValidatorException e) {
			doCatch(e, "doPut failed", EnumError.DATA_VALIDATING, resp);
		} catch (ServiceException e) {
			doCatch(e, "doPut failed", EnumError.SERVICE, resp);
		}
	}
}
