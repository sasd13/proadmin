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
import com.sasd13.javaex.service.ICheckService;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.proadmin.aaa.service.CredentialService;
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

	private static final Logger LOGGER = Logger.getLogger(LogInServlet.class);

	private IValidator<Credential> validator;
	private ICheckService<Credential> checkService;

	@Override
	public void init() throws ServletException {
		super.init();

		validator = new CredentialValidator();
		checkService = new CredentialService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPost");

		try {
			Credential credential = readFromRequest(req).get(0);

			validator.validate(credential);

			if (checkService.contains(credential)) {
				String message = ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(SessionBuilder.build(credential));

				writeToResponse(resp, message);
			} else {
				writeError(resp, EnumError.AAA_LOGIN_FAILED);
			}
		} catch (Exception e) {
			handleError(e, resp);
		}
	}
}
