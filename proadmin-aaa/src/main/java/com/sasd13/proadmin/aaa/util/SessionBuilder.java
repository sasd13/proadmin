package com.sasd13.proadmin.aaa.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.util.SessionIdGenerator;

import com.sasd13.proadmin.aaa.bean.User;
import com.sasd13.proadmin.util.EnumSession;

public class SessionBuilder {

	private static SessionIdGenerator generator = new SessionIdGenerator();

	public static Map<String, String> build(User user) {
		Map<String, String> map = new HashMap<>();

		map.put(EnumSession.USERID.getKey(), user.getUserID());
		map.put(EnumSession.INTERMEDIARY.getKey(), user.getIntermediary());
		map.put(EnumSession.TOKEN.getKey(), generator.generateSessionId());
		map.put(EnumSession.START.getKey(), new SimpleDateFormat(Constants.PATTERN_DATETIME_DEFAULT).format(new Timestamp(System.currentTimeMillis())));

		return map;
	}
}
