package com.sasd13.proadmin.aaa.util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.util.SessionIdGenerator;

import com.sasd13.proadmin.bean.user.User;
import com.sasd13.proadmin.util.EnumSession;

public class SessionBuilder {

	private static SessionIdGenerator generator = new SessionIdGenerator();

	public static Map<String, String> build(User user) {
		Map<String, String> map = new HashMap<>();

		map.put(EnumSession.USERID.getName(), user.getUserID());
		map.put(EnumSession.INTERMEDIARY.getName(), user.getIntermediary());
		map.put(EnumSession.TOKEN.getName(), generator.generateSessionId());
		map.put(EnumSession.START.getName(), String.valueOf(new Timestamp(System.currentTimeMillis())));

		return map;
	}
}
