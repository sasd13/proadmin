/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.ws;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sasd13.javaex.net.ws.rest.LogInWSClient;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.proadmin.aaa.Credential;
import com.sasd13.proadmin.aaa.service.ICredentialReadService;
import com.sasd13.proadmin.aaa.service.impl.CredentialReadService;
import com.sasd13.proadmin.aaa.ws.handler.RESTHandler;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LogInWebService extends HttpServlet {

	private static final long serialVersionUID = 4147483186176202467L;
	private static final Logger LOG = Logger.getLogger(LogInWebService.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Credential credential = getCredentialFromRequest(req);
			ICredentialReadService credentialReadService = new CredentialReadService();
			
			if (credentialReadService.containsCredential(credential)) {
				RESTHandler.writeDataToResponse(req, resp, EnumWSCode.OK, null);
			} else {
				RESTHandler.writeDataToResponse(req, resp, EnumWSCode.ERROR_LOGIN_TEACHER, null);
			}
		} catch (ParserException e) {
			LOG.error(e);
		}		
	}

	@SuppressWarnings("unchecked")
	private Credential getCredentialFromRequest(HttpServletRequest req) throws IOException, ParserException {
		Map<String, String> map = (Map<String, String>) RESTHandler.readDataFromRequest(req, Map.class);
		Credential credential = new Credential(
				map.get(LogInWSClient.PARAM_USERNAME), 
				map.get(LogInWSClient.PARAM_PASSWORD)
		);
		return credential;
	}
}
