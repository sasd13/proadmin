/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.ws;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.SessionIdGenerator;
import org.apache.log4j.Logger;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.ws.rest.LogInWSClient;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.proadmin.aaa.AAAException;
import com.sasd13.proadmin.aaa.bean.Credential;
import com.sasd13.proadmin.aaa.service.CredentialReadService;
import com.sasd13.proadmin.aaa.service.ICredentialReadService;
import com.sasd13.proadmin.aaa.util.Config;
import com.sasd13.proadmin.aaa.util.Names;
import com.sasd13.proadmin.util.net.EnumAAAError;
import com.sasd13.proadmin.util.net.EnumAAASessionInfo;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LogInWebService extends HttpServlet {

	private static final long serialVersionUID = 4147483186176202467L;

	private static final Logger LOG = Logger.getLogger(LogInWebService.class);
	private static final String RESPONSE_CONTENT_TYPE = Config.getInfo(Names.AAA_RESPONSE_CONTENT_TYPE);

	private ICredentialReadService credentialReadService;
	private SessionIdGenerator sessionIdGenerator;

	@Override
	public void init() throws ServletException {
		super.init();

		credentialReadService = new CredentialReadService();
		sessionIdGenerator = new SessionIdGenerator();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("LogInWebService --> doPost");
		
		try {
			Map<String, String> map = (Map<String, String>) ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), Map.class);
			Credential credential = new Credential(map.get(LogInWSClient.PARAMETER_USERNAME), map.get(LogInWSClient.PARAMETER_PASSWORD));

			if (credentialReadService.containsCredential(credential)) {
				resp.setContentType(RESPONSE_CONTENT_TYPE);
				Stream.writeAndClose(resp.getWriter(), ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(getSessionInfos(credential)));
			} else {
				resp.setHeader(EnumHttpHeader.WS_ERROR.getName(), String.valueOf(EnumAAAError.ERROR_LOGIN.getCode()));
			}
		} catch (ParserException | AAAException e) {
			doCatch(e, "LogInWebService --> doPost failed", resp);
		}
	}

	private void doCatch(Exception e, String logMessage, HttpServletResponse resp) throws IOException {
		LOG.error(logMessage, e);

		EnumAAAError aaaError = AAAErrorFactory.make(e);

		resp.setHeader(EnumHttpHeader.WS_ERROR.getName(), String.valueOf(aaaError.getCode()));
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.writeAndClose(resp.getWriter(), aaaError.getLabel());
	}

	private Map<String, String> getSessionInfos(Credential credential) {
		Map<String, String> map = new HashMap<>();

		map.put(EnumAAASessionInfo.USERNAME.getName(), credential.getUsername());
		map.put(EnumAAASessionInfo.TOKEN.getName(), sessionIdGenerator.generateSessionId());
		map.put(EnumAAASessionInfo.BEGIN.getName(), String.valueOf(new Timestamp(System.currentTimeMillis())));

		return map;
	}
}
