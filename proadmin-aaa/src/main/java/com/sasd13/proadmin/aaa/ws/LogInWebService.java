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

import com.sasd13.javaex.io.Stream;
import com.sasd13.javaex.net.ws.rest.LogInWSClient;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.javaex.parser.ParserFactory;
import com.sasd13.proadmin.aaa.entity.Credential;
import com.sasd13.proadmin.aaa.service.CredentialReadService;
import com.sasd13.proadmin.aaa.service.ICredentialReadService;
import com.sasd13.proadmin.aaa.util.Config;
import com.sasd13.proadmin.aaa.util.Names;

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

	@Override
	public void init() throws ServletException {
		super.init();

		credentialReadService = new CredentialReadService();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Map<String, String> map = (Map<String, String>) ParserFactory.make(req.getContentType()).fromString(Stream.readAndClose(req.getReader()), Map.class);
			Credential credential = new Credential(map.get(LogInWSClient.PARAMETER_USERNAME), map.get(LogInWSClient.PARAMETER_PASSWORD));

			if (credentialReadService.containsCredential(credential)) {
				resp.setContentType(RESPONSE_CONTENT_TYPE);

				// TODO : Send token
				// Stream.writeAndClose(resp.getWriter(), ParserFactory.make(RESPONSE_CONTENT_TYPE).toString());
			} else {
				// TODO : Send error
				// resp.setHeader(EnumHttpHeader.WS_ERROR, );
			}
		} catch (ParserException e) {
			LOG.error(e);
			// TODO : Send error
		}
	}
}
