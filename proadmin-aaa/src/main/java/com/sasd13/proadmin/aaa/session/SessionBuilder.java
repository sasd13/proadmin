package com.sasd13.proadmin.aaa.session;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.util.SessionIdGenerator;

import com.sasd13.proadmin.bean.profile.Profile;
import com.sasd13.proadmin.util.aaa.EnumAAASession;

public class SessionBuilder {

	private static SessionIdGenerator generator = new SessionIdGenerator();

	public static Map<String, String> build(Profile profile) {
		Map<String, String> map = new HashMap<>();

		map.put(EnumAAASession.USERNAME.getName(), profile.getUsername());
		map.put(EnumAAASession.NUMBER.getName(), profile.getNumber());
		map.put(EnumAAASession.TOKEN.getName(), generator.generateSessionId());
		map.put(EnumAAASession.START.getName(), String.valueOf(new Timestamp(System.currentTimeMillis())));

		return map;
	}
}
