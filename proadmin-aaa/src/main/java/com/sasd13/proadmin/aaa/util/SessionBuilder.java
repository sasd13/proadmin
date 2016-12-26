package com.sasd13.proadmin.aaa.util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.util.SessionIdGenerator;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.util.aaa.EnumAAASession;

public class SessionBuilder {

	private static SessionIdGenerator generator = new SessionIdGenerator();

	public static Map<String, String> build(Credential credential) {
		Map<String, String> map = new HashMap<>();

		map.put(EnumAAASession.USERNAME.getName(), credential.getUsername());
		map.put(EnumAAASession.TOKEN.getName(), generator.generateSessionId());
		map.put(EnumAAASession.START.getName(), String.valueOf(new Timestamp(System.currentTimeMillis())));

		return map;
	}
}
