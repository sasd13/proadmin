/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.aaa.AAAConstants;
import com.sasd13.proadmin.aaa.dao.DAO;
import com.sasd13.proadmin.aaa.model.UserCreate;
import com.sasd13.proadmin.aaa.model.UserUpdate;
import com.sasd13.proadmin.aaa.service.IUserService;
import com.sasd13.proadmin.aaa.service.ServiceFactory;
import com.sasd13.proadmin.bean.user.User;
import com.sasd13.proadmin.itf.bean.user.UserCreateBean;
import com.sasd13.proadmin.itf.bean.user.UserUpdateBean;
import com.sasd13.proadmin.util.EnumError;

/**
 *
 * @author Samir
 */
@WebServlet("/user")
public class UserServlet extends AAAServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final Logger LOGGER = Logger.getLogger(UserServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] User : GET");

		DAO dao = (DAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);

			if (parameters != null) {
				List<User> users = userService.read(parameters);

				writeToResponse(resp, LOGGER, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(users));
			} else {
				writeError(resp, LOGGER, HttpStatus.SC_EXPECTATION_FAILED, EnumError.AAA);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] User : POST");

		DAO dao = (DAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);

		try {
			UserCreate userCreate = (UserCreate) readFromRequest(req, UserCreateBean.class, UserCreate.class);
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);

			userService.create(userCreate.getUser(), userCreate.getCredential());
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] User : PUT");

		DAO dao = (DAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);

		try {
			UserUpdate userUpdate = (UserUpdate) readFromRequest(req, UserUpdateBean.class, UserUpdate.class);
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);

			if (userUpdate.getCredentials() == null) {
				userService.update(userUpdate.getUser(), null);
			} else {
				User user = userService.find(userUpdate.getCredentials().getOldCredential());

				if (user != null) {
					userService.update(userUpdate.getUser(), userUpdate.getCredentials().getNewCredential());
				} else {
					writeError(resp, LOGGER, HttpStatus.SC_EXPECTATION_FAILED, EnumError.AAA);
				}
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
