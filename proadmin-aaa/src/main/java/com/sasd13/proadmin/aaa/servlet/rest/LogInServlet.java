/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet.rest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.AAAConstants;
import com.sasd13.proadmin.aaa.dao.DAO;
import com.sasd13.proadmin.aaa.service.IUserService;
import com.sasd13.proadmin.aaa.service.ServiceFactory;
import com.sasd13.proadmin.aaa.util.SessionBuilder;
import com.sasd13.proadmin.bean.user.User;
import com.sasd13.proadmin.util.EnumError;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LogInServlet extends AAAServlet {

	private static final long serialVersionUID = 4147483186176202467L;

	private static final Logger LOGGER = Logger.getLogger(LogInServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] Login");

		DAO dao = (DAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);

		try {
			Credential credential = (Credential) readFromRequest(req, Credential.class, null);
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);
			User user = userService.find(credential);

			if (user != null) {
				writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(new Map[] { SessionBuilder.build(user) }));
			} else {
				writeError(resp, LOGGER, HttpStatus.SC_EXPECTATION_FAILED, EnumError.AAA);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
