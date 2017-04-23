/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.controller;

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
import com.sasd13.proadmin.aaa.bean.User;
import com.sasd13.proadmin.aaa.bean.UserCreate;
import com.sasd13.proadmin.aaa.bean.UserUpdate;
import com.sasd13.proadmin.aaa.dao.DAO;
import com.sasd13.proadmin.aaa.service.IUserService;
import com.sasd13.proadmin.aaa.service.ServiceFactory;
import com.sasd13.proadmin.aaa.util.Constants;
import com.sasd13.proadmin.aaa.util.adapter.itf2bean.UserCreateAdapterI2B;
import com.sasd13.proadmin.aaa.util.adapter.itf2bean.UserUpdateAdapterI2B;
import com.sasd13.proadmin.itf.bean.user.create.UserCreateBean;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateBean;
import com.sasd13.proadmin.util.EnumError;

/**
 *
 * @author Samir
 */
@WebServlet("/user")
public class UserController extends Controller {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] User : read");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
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
		LOGGER.info("[Proadmin-AAA] User : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			UserCreateBean userCreateBean = readFromRequest(req, UserCreateBean.class);
			UserCreate userCreate = new UserCreateAdapterI2B().adapt(userCreateBean);
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);

			userService.create(userCreate.getUser(), userCreate.getCredential());
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] User : update");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		boolean updated = false;

		try {
			UserUpdateBean userUpdateBean = readFromRequest(req, UserUpdateBean.class);
			UserUpdate userUpdate = new UserUpdateAdapterI2B().adapt(userUpdateBean);
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);

			if (userUpdate.getUser() == null) {
				userService.update(userUpdate.getUser());
				updated = true;
			}

			if (userUpdate.getCredentials() != null) {
				updated = userService.update(userUpdate.getCredentials().getPrevious(), userUpdate.getCredentials().getCurrent()) || updated;
			}

			if (!updated) {
				writeError(resp, LOGGER, HttpStatus.SC_EXPECTATION_FAILED, EnumError.AAA);
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
