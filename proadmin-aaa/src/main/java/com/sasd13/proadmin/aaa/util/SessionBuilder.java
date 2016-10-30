package com.sasd13.proadmin.aaa.util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.util.SessionIdGenerator;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.util.ws.EnumAAASession;

public class SessionBuilder {

	public static Map<String, String> build(Credential credential) {
		Map<String, String> map = new HashMap<>();

		map.put(EnumAAASession.USERNAME.getName(), credential.getUsername());
		map.put(EnumAAASession.TOKEN.getName(), new SessionIdGenerator().generateSessionId());
		map.put(EnumAAASession.BEGIN.getName(), String.valueOf(new Timestamp(System.currentTimeMillis())));

		return map;
	}
}
