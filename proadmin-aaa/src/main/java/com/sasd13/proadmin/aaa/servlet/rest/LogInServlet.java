/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.proadmin.aaa.AAAConstants;
import com.sasd13.proadmin.aaa.EnumParameter;
import com.sasd13.proadmin.aaa.dao.IProfileDAO;
import com.sasd13.proadmin.aaa.service.ProfileService;
import com.sasd13.proadmin.aaa.session.SessionBuilder;
import com.sasd13.proadmin.aaa.util.validator.CredentialValidator;
import com.sasd13.proadmin.bean.profile.Profile;
import com.sasd13.proadmin.util.exception.EnumError;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LogInServlet extends AAAServlet {

	private static final long serialVersionUID = 4147483186176202467L;

	private static final Logger LOGGER = Logger.getLogger(LogInServlet.class);
	private static final int HTTP_EXPECTATION_FAILED = 417;

	private IValidator<Credential> validator;

	@Override
	public void init() throws ServletException {
		super.init();

		validator = new CredentialValidator();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("doPost");

		IProfileDAO dao = (IProfileDAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);

		try {
			Credential credential = readFromRequest(req);
			IReadService<Profile> readService = new ProfileService(dao);

			validator.validate(credential);

			List<Profile> profiles = readService.read(getParameters(credential));

			if (!profiles.isEmpty()) {
				writeToResponse(resp, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(new Map[] { SessionBuilder.build(profiles.get(0)) }));
			} else {
				writeError(resp, HTTP_EXPECTATION_FAILED, EnumError.AAA_LOGIN);
			}
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	private Map<String, String[]> getParameters(Credential credential) {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(EnumParameter.USERNAME.getName(), new String[] { credential.getUsername() });
		parameters.put(EnumParameter.PASSWORD.getName(), new String[] { credential.getPassword() });

		return parameters;
	}
}
