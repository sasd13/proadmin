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
import com.sasd13.javaex.util.DataParserException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.TeacherDAO;
import com.sasd13.proadmin.util.Parameter;
import com.sasd13.proadmin.ws.db.JDBCDAO;
import com.sasd13.proadmin.ws.db.JDBCPasswordDAO;
import com.sasd13.proadmin.ws.rest.handler.RESTHandler;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LoginWebService extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = 0;
		
		JDBCDAO dao = new JDBCDAO();
		
		try {
			Map<String, String> logins = RESTHandler.readAndParseDataFromRequest(req, Map.class);
			String number = logins.get(Parameter.NUMBER.getName());
			
			Map<String, String[]> parameters = new HashMap<String, String[]>();
			parameters.put(Parameter.NUMBER.getName(), new String[]{ number });
			
			dao.open();
			List<Teacher> list = ((TeacherDAO) dao.getEntityDAO(Teacher.class)).select(parameters);
			
			if (list.isEmpty()) {
				id = 0;
			} else {
				Teacher teacher = list.get(0);
				
				String candidate = logins.get(Parameter.PASSWORD.getName());
				String password = selectPasswordFromDAO(dao, teacher);
				
				id = (password.equals(candidate)) ? teacher.getId() : -1;
			}
		} catch (DataParserException | DAOException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		
		try {
			RESTHandler.parseAndWriteDataToResponse(req, resp, id);
		} catch (DataParserException e) {
			e.printStackTrace();
		}
	}
	
	private String selectPasswordFromDAO(JDBCDAO dao, Teacher teacher) {
		return new JDBCPasswordDAO(dao.getConnection()).select(teacher.getId());
	}
}
