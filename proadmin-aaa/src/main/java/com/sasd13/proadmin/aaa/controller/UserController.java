/*
 * To change this license header, choose License Headers in LeadEvaluation Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.net.EnumHttpStatus;
import com.sasd13.proadmin.aaa.bean.User;
import com.sasd13.proadmin.aaa.bean.UserCreate;
import com.sasd13.proadmin.aaa.bean.UserUpdate;
import com.sasd13.proadmin.aaa.dao.DAO;
import com.sasd13.proadmin.aaa.service.IUserService;
import com.sasd13.proadmin.aaa.service.ServiceFactory;
import com.sasd13.proadmin.aaa.util.Constants;
import com.sasd13.proadmin.aaa.util.adapter.bean2itf.UserAdapterB2I;
import com.sasd13.proadmin.aaa.util.adapter.itf2bean.UserCreateAdapterI2B;
import com.sasd13.proadmin.aaa.util.adapter.itf2bean.UserUpdateAdapterI2B;
import com.sasd13.proadmin.itf.bean.user.UserBean;
import com.sasd13.proadmin.itf.bean.user.UserResponseBean;
import com.sasd13.proadmin.itf.bean.user.create.UserCreateBean;
import com.sasd13.proadmin.itf.bean.user.create.UserCreateRequestBean;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateBean;
import com.sasd13.proadmin.itf.bean.user.update.UserUpdateRequestBean;

/**
 *
 * @author Samir
 */
@WebServlet("/users")
public class UserController extends Controller {

	private static final long serialVersionUID = 1073440009453108500L;

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] User : read");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);
		Map<String, String[]> parameters = req.getParameterMap();
		IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);

		try {
			List<User> results = userService.read(parameters);
			UserResponseBean responseBean = new UserResponseBean();
			List<UserBean> list = new ArrayList<>();
			UserAdapterB2I adapter = new UserAdapterB2I();

			for (User result : results) {
				list.add(adapter.adapt(result));
			}

			responseBean.setData(list);
			addHeaders(responseBean, list.size());

			writeToResponse(resp, responseBean);
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] User : create");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			UserCreateRequestBean requestBean = readFromRequest(req, UserCreateRequestBean.class);
			UserCreateBean userCreateBean = requestBean.getData();
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

		try {
			UserUpdateRequestBean requestBean = readFromRequest(req, UserUpdateRequestBean.class);
			UserUpdateBean userUpdateBean = requestBean.getData();
			UserUpdate userUpdate = new UserUpdateAdapterI2B().adapt(userUpdateBean);
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);

			if (userUpdate.getUser() != null) {
				userService.update(userUpdate.getUser());
			}

			boolean updated = true;

			if (userUpdate.getCredentials() != null) {
				updated = userService.update(userUpdate.getCredentials().getPrevious(), userUpdate.getCredentials().getCurrent());
			}

			if (!updated) {
				resp.sendError(EnumHttpStatus.EXPECTATION_FAILED.getCode());
			}
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
