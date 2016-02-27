/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.db.IDAO;
import com.sasd13.javaex.db.Persistence;
import com.sasd13.javaex.util.DataParserException;
import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.util.URLParameter;

import db.JDBCDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samir
 */
@WebServlet("/login")
public class LoginWebService extends HttpServlet {
	
	private IDAO dao = JDBCDAO.getInstance();
	private Persistence persistence;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		persistence = new Persistence(dao);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Teacher teacherFromRequest = (Teacher) IOService.readAndParseDataFromRequest(req, Teacher.class);
			
			Map<String, String[]> parameters = new HashMap<String, String[]>();
			parameters.put(URLParameter.NUMBER.getName(), new String[]{teacherFromRequest.getNumber()});
			
			List<Teacher> list = (List<Teacher>) persistence.read(parameters, Teacher.class);
			
			long id;
			
			if (list.isEmpty() || list.size() > 1) {
				id = 0;
			} else {
				Teacher teacher = list.get(0);
				
				id = (teacherFromRequest.getPassword().equals(teacher.getPassword()))
						? teacher.getId()
						: -1;
			}
			
			IOService.parseAndWriteDataToResponse(req, resp, id);
		} catch (DataParserException e) {
			e.printStackTrace();
		}
	}
}
