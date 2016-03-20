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

import com.sasd13.javaex.db.Persistence;
import com.sasd13.javaex.util.DataParserException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.Parameter;
import com.sasd13.proadmin.ws.db.JDBCDAO;
import com.sasd13.proadmin.ws.rest.handler.RESTHandler;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LoginWebService extends HttpServlet {
	
	private Persistence persistence;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		persistence = new Persistence(JDBCDAO.getInstance());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Map<String, String> login = RESTHandler.readAndParseDataFromRequest(req, Map.class);
			String number = login.get(Parameter.NUMBER.getName());
			
			Map<String, String[]> parameters = new HashMap<String, String[]>();
			parameters.put(Parameter.NUMBER.getName(), new String[]{ number });
			
			List<Teacher> list = (List<Teacher>) persistence.read(parameters, Teacher.class);
			
			long id;
			
			if (list.isEmpty()) {
				id = 0;
			} else {
				Teacher teacher = list.get(0);
				String password = login.get(Parameter.PASSWORD.getName());
				
				id = (teacher.isPasswordMatching(password))
						? teacher.getId()
						: -1;
			}
			
			RESTHandler.parseAndWriteDataToResponse(req, resp, id);
		} catch (DataParserException e) {
			e.printStackTrace();
		}
	}
}
