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

import org.apache.log4j.Logger;

import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.aaa.AAAConstants;
import com.sasd13.proadmin.aaa.dao.DAO;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.aaa.model.UserCreate;
import com.sasd13.proadmin.aaa.model.UserUpdate;
import com.sasd13.proadmin.aaa.service.IUserService;
import com.sasd13.proadmin.aaa.service.ServiceFactory;
import com.sasd13.proadmin.aaa.util.adapter.b2m.UserCreateBeanToUserCreateAdapter;
import com.sasd13.proadmin.aaa.util.adapter.b2m.UserUpdateBeanToUserUpdateAdapter;
import com.sasd13.proadmin.itf.bean.user.UserCreateBean;
import com.sasd13.proadmin.itf.bean.user.UserUpdateBean;
import com.sasd13.proadmin.util.error.EnumError;

/**
 *
 * @author Samir
 */
@WebServlet("/user")
public class UserServlet extends AAAServlet {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final Logger LOGGER = Logger.getLogger(UserServlet.class);
	private static final int HTTP_EXPECTATION_FAILED = 417;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] User : GET");

		DAO dao = (DAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();

		try {
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);

			if (parameters != null) {
				List<User> users = userService.read(parameters);

				writeToResponse(resp, ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(users));
			} else {
				writeError(resp, HTTP_EXPECTATION_FAILED, EnumError.AAA);
			}
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] User : POST");

		DAO dao = (DAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);

		try {
			UserCreateBean userCreateBean = (UserCreateBean) readFromRequest(req, UserCreateBean.class);
			UserCreate userCreate = new UserCreateBeanToUserCreateAdapter().adapt(userCreateBean);
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);

			userService.create(userCreate.getUser(), userCreate.getCredential());
		} catch (Exception e) {
			handleError(e, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] User : PUT");

		DAO dao = (DAO) req.getAttribute(AAAConstants.REQ_ATTR_DAO);

		try {
			UserUpdateBean userUpdateBean = (UserUpdateBean) readFromRequest(req, UserUpdateBean.class);
			UserUpdate userUpdate = new UserUpdateBeanToUserUpdateAdapter().adapt(userUpdateBean);
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);

			if (userUpdate.getCredentials() == null) {
				userService.update(userUpdate.getUserID(), userUpdate.getUser(), null);
			} else {
				User user = userService.find(userUpdate.getCredentials().getOldCredential());

				if (user != null) {
					userService.update(userUpdate.getUserID(), userUpdate.getUser(), userUpdate.getCredentials().getNewCredential());
				} else {
					writeError(resp, HTTP_EXPECTATION_FAILED, EnumError.AAA);
				}
			}
		} catch (Exception e) {
			handleError(e, resp);
		}
	}
}
