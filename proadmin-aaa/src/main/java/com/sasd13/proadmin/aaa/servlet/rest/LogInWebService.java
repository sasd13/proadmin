/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.servlet.rest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.javaex.util.EnumHttpHeader;
import com.sasd13.javaex.util.conf.AppProperties;
import com.sasd13.proadmin.aaa.AAAException;
import com.sasd13.proadmin.aaa.bean.Credential;
import com.sasd13.proadmin.aaa.service.CredentialReadService;
import com.sasd13.proadmin.aaa.service.ICredentialReadService;
import com.sasd13.proadmin.aaa.util.Names;
import com.sasd13.proadmin.aaa.util.SessionBuilder;
import com.sasd13.proadmin.util.net.EnumAAAError;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LogInWebService extends HttpServlet {

	private static final long serialVersionUID = 4147483186176202467L;

	private static final Logger LOG = Logger.getLogger(LogInWebService.class);
	private static final String PARAMETER_USERNAME = "username";
	private static final String PARAMETER_PASSWORD = "password";
	private static final String RESPONSE_CONTENT_TYPE = AppProperties.getProperty(Names.AAA_RESPONSE_CONTENT_TYPE);

	private ICredentialReadService credentialReadService;

	@Override
	public void init() throws ServletException {
		super.init();

		credentialReadService = new CredentialReadService();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("LogInWebService --> doPost");

		try {
			Map<String, String> map = (Map<String, String>) ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), Map.class);
			Credential credential = new Credential(map.get(PARAMETER_USERNAME), map.get(PARAMETER_PASSWORD));

			if (credentialReadService.containsCredential(credential)) {
				resp.setContentType(RESPONSE_CONTENT_TYPE);
				Stream.writeAndClose(resp.getWriter(), ParserFactory.make(RESPONSE_CONTENT_TYPE).toString(SessionBuilder.build(credential)));
			} else {
				throw new AAAException("Username/password not matching");
			}
		} catch (ParserException e) {
			doCatch(e, "LogInWebService --> doPost failed", EnumAAAError.ERROR_PARSING_DATA, resp);
		} catch (AAAException e) {
			doCatch(e, "LogInWebService --> doPost failed", EnumAAAError.ERROR_SERVICE, resp);
		}
	}

	private void doCatch(Exception e, String logMessage, EnumAAAError aaaError, HttpServletResponse resp) throws IOException {
		LOG.error(logMessage, e);
		resp.setHeader(EnumHttpHeader.WS_ERROR.getName(), String.valueOf(aaaError.getCode()));
		resp.setContentType(RESPONSE_CONTENT_TYPE);
		Stream.writeAndClose(resp.getWriter(), e.getMessage());
	}
}
