/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.net.ws.rest.LogInWSClient;
import com.sasd13.javaex.parser.ParserException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.TeacherDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.ws.EnumWSCode;
import com.sasd13.proadmin.ws.db.JDBCDAO;
import com.sasd13.proadmin.ws.db.JDBCPasswordDAO;
import com.sasd13.proadmin.ws.rest.handler.RESTHandler;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LogInWebService extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JDBCDAO dao = JDBCDAO.create();

		try {
			Map<String, String> credentials = (Map<String, String>) RESTHandler.readDataFromRequest(req, Map.class);
			String number = credentials.get(LogInWSClient.PARAM_USERNAME);

			Map<String, String[]> parameters = new HashMap<String, String[]>();
			parameters.put(EnumParameter.NUMBER.getName(), new String[] { number });

			dao.open();
			List<Teacher> list = ((TeacherDAO) dao.getEntityDAO(Teacher.class)).select(parameters);

			if (list.isEmpty()) {
				RESTHandler.writeDataToResponse(req, resp, EnumWSCode.ERROR_LOGIN_TEACHER_NUMBER, null);
			} else {
				Teacher teacher = list.get(0);
				String candidate = credentials.get(LogInWSClient.PARAM_PASSWORD);

				if (passwordMatches(dao, candidate, teacher)) {
					RESTHandler.writeDataToResponse(req, resp, EnumWSCode.OK, teacher.getId());
				} else {
					RESTHandler.writeDataToResponse(req, resp, EnumWSCode.ERROR_LOGIN_TEACHER_PASSWORD, null);
				}
			}
		} catch (ParserException | DAOException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
	}

	private boolean passwordMatches(JDBCDAO dao, String password, Teacher teacher) {
		return new JDBCPasswordDAO(dao.getConnection()).contains(password, teacher.getId());
	}
}
