/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.ICredentialReadService;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.proadmin.aaa.service.CredentialReadService;
import com.sasd13.proadmin.aaa.util.SessionBuilder;
import com.sasd13.proadmin.aaa.validator.CredentialValidator;
import com.sasd13.proadmin.util.exception.EnumError;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LogInServlet extends AAAServlet {

	private static final long serialVersionUID = 4147483186176202467L;

	private static final Logger LOG = Logger.getLogger(LogInServlet.class);

	private IValidator<Credential> validator;
	private ICredentialReadService credentialReadService;

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	@Override
	public void init() throws ServletException {
		super.init();

		validator = new CredentialValidator();
		credentialReadService = new CredentialReadService();
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
