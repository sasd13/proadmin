package com.sasd13.proadmin.aaa.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.util.SessionIdGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.aaa.service.IUserService;
import com.sasd13.proadmin.aaa.util.Constants;
import com.sasd13.proadmin.aaa.util.adapter.bean2itf.UserAdapterB2I;
import com.sasd13.proadmin.itf.bean.user.log.AuthenticationResponseBean;
import com.sasd13.proadmin.util.EnumError;
import com.sasd13.proadmin.util.EnumSession;

@RestController
public class AuthenticationController extends Controller {

	private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);

	@Autowired
	private IUserService userService;

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<AuthenticationResponseBean> create(@RequestBody Credential credential) {
		LOGGER.info("[Proadmin-Backend] User : login");

		try {
			User user = userService.find(credential);
			AuthenticationResponseBean responseBean = new AuthenticationResponseBean();

			if (user != null) {
				responseBean.setSession(buildSession(user));
				responseBean.setUser(new UserAdapterB2I().adapt(user));
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

	public static Map<String, String> buildSession(User user) {
		Map<String, String> map = new HashMap<>();

		map.put(EnumSession.USERID.getKey(), user.getUserID());
		map.put(EnumSession.INTERMEDIARY.getKey(), user.getIntermediary());
		map.put(EnumSession.TOKEN.getKey(), new SessionIdGenerator().generateSessionId());
		map.put(EnumSession.START.getKey(), new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).format(new Timestamp(System.currentTimeMillis())));

		return map;
	}
}
