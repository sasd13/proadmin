package com.sasd13.proadmin.aaa.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.bean.AuthenticatedUser;
import com.sasd13.proadmin.aaa.service.IAuthenticationService;
import com.sasd13.proadmin.itf.bean.user.log.AuthenticationResponseBean;
import com.sasd13.proadmin.util.EnumError;

@RestController
public class AuthenticationController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);

	@Autowired
	private IAuthenticationService authenticationService;

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponseBean> create(@RequestBody Credential credential) {
		LOGGER.info("[Proadmin-Backend] User : login");

		try {
			AuthenticatedUser authenticatedUser = authenticationService.logIn(credential);
			AuthenticationResponseBean responseBean = new AuthenticationResponseBean();

			if (authenticatedUser != null) {
				responseBean.setSession(authenticatedUser.getSession());
				responseBean.setUser(authenticatedUser.getUserBean());
			} else {
				Map<String, String> errors = new HashMap<String, String>();
				String code = String.valueOf(EnumError.AUTHENTICATION.getCode());
				// String message = bundle.getString(EnumError.AUTHENTICATION.getBundleKey());
				String message = "Authentication failed";

				errors.put(code, message);
				responseBean.setErrors(errors);
			}

			return new ResponseEntity<AuthenticationResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e);
		}

		return new ResponseEntity<AuthenticationResponseBean>(HttpStatus.EXPECTATION_FAILED);
	}
}
