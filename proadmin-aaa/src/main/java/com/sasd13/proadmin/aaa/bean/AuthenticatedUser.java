package com.sasd13.proadmin.aaa.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.util.SessionIdGenerator;

import com.sasd13.proadmin.aaa.util.Constants;
import com.sasd13.proadmin.itf.bean.user.UserBean;
import com.sasd13.proadmin.util.EnumSession;

public class AuthenticatedUser {

	private UserBean userBean;
	private Map<String, String> session;

	public AuthenticatedUser(UserBean userBean) {
		this.userBean = userBean;

		buildSession();
	}

	private void buildSession() {
		session = new HashMap<>();

		session.put(EnumSession.USERID.getKey(), userBean.getCoreInfo().getUserID());
		session.put(EnumSession.INTERMEDIARY.getKey(), userBean.getCoreInfo().getIntermediary());
		session.put(EnumSession.TOKEN.getKey(), new SessionIdGenerator().generateSessionId());
		session.put(EnumSession.START.getKey(), new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).format(new Timestamp(System.currentTimeMillis())));
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public Map<String, String> getSession() {
		return session;
	}
}
