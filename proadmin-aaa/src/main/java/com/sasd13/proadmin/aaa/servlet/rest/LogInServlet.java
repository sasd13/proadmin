/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.ICheckService;
import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.proadmin.aaa.AAAConstants;
import com.sasd13.proadmin.aaa.dao.ICredentialDAO;
import com.sasd13.proadmin.aaa.service.CredentialService;
import com.sasd13.proadmin.aaa.session.SessionBuilder;
import com.sasd13.proadmin.aaa.util.validator.CredentialValidator;
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

	@Override
	public void init() throws ServletException {
		super.init();

		validator = new CredentialValidator();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPost");

		ICredentialDAO dao = (ICredentialDAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);

		try {
			Credential credential = readFromRequest(req).get(0);
			ICheckService<Credential> checkService = new CredentialService(dao);

			validator.validate(credential);

			if (checkService.contains(credential)) {
				writeToResponse(resp, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(new Map[] { SessionBuilder.build(credential) }));
			} else {
				resp.setStatus(HttpURLConnection.HTTP_UNAUTHORIZED);
				writeError(resp, EnumError.AAA_LOGIN);
			}
		} catch (Exception e) {
			handleError(e, resp);
		}
	}
}
