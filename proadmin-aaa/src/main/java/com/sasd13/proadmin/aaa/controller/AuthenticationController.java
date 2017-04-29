/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.i18n.TranslationBundle;
import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.bean.User;
import com.sasd13.proadmin.aaa.dao.DAO;
import com.sasd13.proadmin.aaa.service.IUserService;
import com.sasd13.proadmin.aaa.service.ServiceFactory;
import com.sasd13.proadmin.aaa.util.Constants;
import com.sasd13.proadmin.aaa.util.SessionBuilder;
import com.sasd13.proadmin.itf.bean.user.log.AuthenticationResponseBean;
import com.sasd13.proadmin.util.EnumError;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class AuthenticationController extends Controller {

	private static final long serialVersionUID = 4147483186176202467L;

	private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);

	private TranslationBundle bundle;

	@Override
	public void init() throws ServletException {
		super.init();

		bundle = new TranslationBundle(Locale.ENGLISH);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("[Proadmin-AAA] Authentication");

		DAO dao = (DAO) req.getAttribute(Constants.REQ_ATTR_DAO);

		try {
			Credential credential = readFromRequest(req, Credential.class);
			IUserService userService = (IUserService) ServiceFactory.make(IUserService.class, dao);
			User user = userService.find(credential);
			AuthenticationResponseBean responseBean = new AuthenticationResponseBean();

			if (user != null) {
				responseBean.setData(SessionBuilder.build(user));
			} else {
				Map<String, String> errors = new HashMap<String, String>();
				String code = String.valueOf(EnumError.AUTHENTICATION.getCode());
				String message = bundle.getString(EnumError.AUTHENTICATION.getBundleKey());

				errors.put(code, message);
				responseBean.setErrors(errors);
			}

			writeToResponse(resp, responseBean);
		} catch (Exception e) {
			handleError(resp, LOGGER, e);
		}
	}
}
