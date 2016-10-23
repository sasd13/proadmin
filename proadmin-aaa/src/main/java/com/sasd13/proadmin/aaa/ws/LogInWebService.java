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

import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.net.ws.rest.LogInWSClient;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.proadmin.aaa.Credential;
import com.sasd13.proadmin.aaa.dao.CredentialDAO;
import com.sasd13.proadmin.aaa.dao.impl.JDBCCredentialDAO;
import com.sasd13.proadmin.aaa.ws.handler.RESTHandler;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LogInWebService extends HttpServlet {

	private static final long serialVersionUID = 4147483186176202467L;
	
	private CredentialDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();

		dao = new JDBCCredentialDAO();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Map<String, String> map = (Map<String, String>) RESTHandler.readDataFromRequest(req, Map.class);
			String number = map.get(LogInWSClient.PARAM_USERNAME);
			String candidate = map.get(LogInWSClient.PARAM_PASSWORD);
			Credential credential = new Credential(number, candidate);

			dao.open();

			if (dao.contains(credential)) {
				RESTHandler.writeDataToResponse(req, resp, EnumWSCode.OK, null);
			} else {
				RESTHandler.writeDataToResponse(req, resp, EnumWSCode.ERROR_LOGIN_TEACHER, null);
			}
		} catch (ParserException | DAOException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
	}
}
